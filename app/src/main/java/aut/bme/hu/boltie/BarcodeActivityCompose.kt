package aut.bme.hu.boltie

import android.annotation.SuppressLint
import android.content.Context
import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.camera.core.*
import androidx.camera.lifecycle.ProcessCameraProvider
import androidx.camera.view.PreviewView
import androidx.compose.foundation.layout.*
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import android.util.Size
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalLifecycleOwner
import androidx.compose.ui.viewinterop.AndroidView
import androidx.core.content.ContextCompat
import aut.bme.hu.boltie.ui.theme.BoltieTheme
import com.google.mlkit.vision.barcode.BarcodeScannerOptions
import com.google.mlkit.vision.barcode.BarcodeScanning
import com.google.mlkit.vision.barcode.common.Barcode
import com.google.mlkit.vision.common.InputImage
import java.util.concurrent.Executors
import kotlin.coroutines.resume
import kotlin.coroutines.suspendCoroutine

class BarcodeActivityCompose : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            BoltieTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colors.background
                ) {
                    CreateCameraPreview()
                }
            }
        }
    }

    @SuppressLint("UnsafeOptInUsageError")
    @Composable
    private fun CreateCameraPreview() {
        val context = LocalContext.current
        val lifecycleOwner = LocalLifecycleOwner.current
        val lensFacing = CameraSelector.LENS_FACING_BACK
        val imageCapture = remember {ImageCapture.Builder().build()}

        val preview = Preview.Builder().setTargetResolution(Size(1280, 720)).build()
        val cameraSelector = CameraSelector.Builder()
            .requireLensFacing(lensFacing)
            .build()

        val imageAnalysis = ImageAnalysis.Builder()
            .setTargetResolution(Size(1280, 720))
            //.setOutputImageFormat(ImageAnalysis.OUTPUT_IMAGE_FORMAT_YUV_420_888)
            .setBackpressureStrategy(ImageAnalysis.STRATEGY_KEEP_ONLY_LATEST)
            .build()
        imageAnalysis.setAnalyzer(ContextCompat.getMainExecutor(this)) { image ->
            val img = image.image
            if (img != null) {
                val inputImage = InputImage.fromMediaImage(img, image.imageInfo.rotationDegrees)

                // Process image searching for barcodes
                val options = BarcodeScannerOptions.Builder()
                    .setBarcodeFormats(Barcode.FORMAT_QR_CODE)
                    .setBarcodeFormats(Barcode.FORMAT_UPC_A)
                    .setBarcodeFormats(Barcode.FORMAT_UPC_E)
                    .build()

                val scanner = BarcodeScanning.getClient(options)
                scanner.process(inputImage)
                    .addOnSuccessListener { barcodes ->
                        for (barcode in barcodes) {
                            barcode.displayValue?.let { Log.i("ScannedBarcode", "Barcode value: $it") } ?: Log.w("ScannedBarcode", "Found something but the value could not be retrieved")
                        }
                    }
                    .addOnFailureListener { }
            }

            image.close()
        }

        val previewView = remember {PreviewView(context)}
        LaunchedEffect(lensFacing) {
            val cameraProvider = context.getCameraProvider()
            cameraProvider.unbindAll()
            cameraProvider.bindToLifecycle(
                lifecycleOwner,
                cameraSelector,
                imageAnalysis,
                preview,
                imageCapture
            )
            preview.setSurfaceProvider(previewView.surfaceProvider)
        }

        Column(modifier = Modifier.fillMaxSize()) {
            AndroidView({previewView}, modifier = Modifier.fillMaxSize()) { }
            Row(modifier = Modifier.fillMaxSize()) {
                Text(text = "Kérlek, olvasd be az eladandó termék vonalkódját!")
            }
        }
    }
}

suspend fun Context.getCameraProvider(): ProcessCameraProvider = suspendCoroutine { continuation ->
    ProcessCameraProvider.getInstance(this).also { cameraProvider ->
        cameraProvider.addListener({
            continuation.resume(cameraProvider.get())
        }, ContextCompat.getMainExecutor(this))
    }
}

/*class BarcodeAnalyzer : ImageAnalysis.Analyzer {
    @SuppressLint("UnsafeOptInUsageError")
    override fun analyze(image: ImageProxy) {
        val img = image.image
        if (img != null) {
            val inputImage = InputImage.fromMediaImage(img, image.imageInfo.rotationDegrees)

            // Process image searching for barcodes
            val options = BarcodeScannerOptions.Builder()
                .setBarcodeFormats(Barcode.FORMAT_QR_CODE)
                .setBarcodeFormats(Barcode.FORMAT_UPC_A)
                .setBarcodeFormats(Barcode.FORMAT_UPC_E)
                .build()

            val scanner = BarcodeScanning.getClient(options)

            scanner.process(inputImage)
                .addOnSuccessListener { barcodes ->
                    for (barcode in barcodes) {
                        barcode.displayValue?.let { Log.i("ScannedBarcode", it) }
                    }
                }
                .addOnFailureListener { }
        }

        image.close()
    }
}*/



