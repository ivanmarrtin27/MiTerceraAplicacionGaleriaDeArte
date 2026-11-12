package com.example.miterceraaplicaciongaleriadearte

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.miterceraaplicaciongaleriadearte.ui.theme.MiTerceraAplicacionGaleriaDeArteTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            MiTerceraAplicacionGaleriaDeArteTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    GaleriaArteApp(
                        modifier = Modifier.padding(innerPadding)
                    )
                }
            }
        }
    }
}

@Composable
fun GaleriaArteApp(modifier: Modifier = Modifier) {
    var obraActual by remember { mutableStateOf(1) }

    val imagenObra = when (obraActual) {
        1 -> R.drawable.las_meninas
        2 -> R.drawable.inmaculada_concepcion
        3 -> R.drawable.saturno_devorando
        else -> R.drawable.el_aquelarre
    }

    val tituloObra = when (obraActual) {
        1 -> R.string.titulo_obra_1
        2 -> R.string.titulo_obra_2
        3 -> R.string.titulo_obra_3
        else -> R.string.titulo_obra_4
    }

    val artistaObra = when (obraActual) {
        1 -> R.string.artista_obra_1
        2 -> R.string.artista_obra_2
        3 -> R.string.artista_obra_3
        else -> R.string.artista_obra_4
    }

    val anioObra = when (obraActual) {
        1 -> R.string.anio_obra_1
        2 -> R.string.anio_obra_2
        3 -> R.string.anio_obra_3
        else -> R.string.anio_obra_4
    }

    val descObra = when (obraActual) {
        1 -> R.string.desc_obra_1
        2 -> R.string.desc_obra_2
        3 -> R.string.desc_obra_3
        else -> R.string.desc_obra_4
    }

    Column(
        modifier = modifier
            .fillMaxSize()
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.SpaceBetween
    ) {
        Spacer(modifier = Modifier.height(8.dp))

        ZonaImagen(
            imagen = imagenObra,
            descripcion = stringResource(descObra),
            modifier = Modifier
                .weight(1f)
                .fillMaxWidth()
        )

        Spacer(modifier = Modifier.height(16.dp))

        ZonaDescripcion(
            titulo = stringResource(tituloObra),
            artista = stringResource(artistaObra),
            anio = stringResource(anioObra)
        )

        Spacer(modifier = Modifier.height(16.dp))

        ZonaBotones(
            onAnterior = {
                obraActual = if (obraActual == 1) 4 else obraActual - 1
            },
            onSiguiente = {
                obraActual = if (obraActual == 4) 1 else obraActual + 1
            }
        )
    }
}

@Composable
fun ZonaImagen(
    imagen: Int,
    descripcion: String,
    modifier: Modifier = Modifier
) {
    Surface(
        modifier = modifier,
        border = BorderStroke(2.dp, MaterialTheme.colorScheme.outline),
        shadowElevation = 4.dp
    ) {
        Image(
            painter = painterResource(imagen),
            contentDescription = descripcion,
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp),
            contentScale = ContentScale.Fit
        )
    }
}

@Composable
fun ZonaDescripcion(
    titulo: String,
    artista: String,
    anio: String,
    modifier: Modifier = Modifier
) {
    Surface(
        modifier = modifier.fillMaxWidth(),
        color = MaterialTheme.colorScheme.surfaceVariant,
        shadowElevation = 2.dp
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                text = titulo,
                fontSize = 22.sp,
                fontWeight = FontWeight.Bold,
                textAlign = TextAlign.Center
            )
            Spacer(modifier = Modifier.height(8.dp))
            Text(
                text = "$artista ($anio)",
                fontSize = 16.sp,
                textAlign = TextAlign.Center
            )
        }
    }
}

@Composable
fun ZonaBotones(
    onAnterior: () -> Unit,
    onSiguiente: () -> Unit,
    modifier: Modifier = Modifier
) {
    Row(
        modifier = modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Button(
            onClick = onAnterior,
            modifier = Modifier.weight(1f)
        ) {
            Text(text = stringResource(R.string.anterior))
        }
        Spacer(modifier = Modifier.width(16.dp))
        Button(
            onClick = onSiguiente,
            modifier = Modifier.weight(1f)
        ) {
            Text(text = stringResource(R.string.siguiente))
        }
    }
}

@Preview(showBackground = true)
@Composable
fun GaleriaArtePreview() {
    MiTerceraAplicacionGaleriaDeArteTheme {
        GaleriaArteApp()
    }
}
