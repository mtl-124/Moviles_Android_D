package com.saludplus.citas.ui.screens.auth

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.saludplus.citas.R
import com.saludplus.citas.ui.theme.AzulMarino
import com.saludplus.citas.ui.theme.AzulPrimario
import com.saludplus.citas.ui.theme.GrisTexto

@Composable
fun SplashScreen(
    onIrALogin: () -> Unit,
    onIrARegistro: () -> Unit
) {
    Surface(
        modifier = Modifier.fillMaxSize(),
        color = Color.White
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .statusBarsPadding()
                .navigationBarsPadding()
                .padding(horizontal = 24.dp, vertical = 16.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Spacer(modifier = Modifier.height(16.dp))

            // a. Logo SaludPlus
            Image(
                painter = painterResource(id = R.drawable.logo_saludplus),
                contentDescription = "Logo SaludPlus",
                modifier = Modifier.height(90.dp),
                contentScale = ContentScale.Fit
            )

            Spacer(modifier = Modifier.height(8.dp))

            // b. Título Clínica SaludPlus
            Text(
                text = "Clínica",
                fontSize = 28.sp,
                fontWeight = FontWeight.SemiBold,
                color = AzulMarino,
                textAlign = TextAlign.Center
            )
            Text(
                text = "SaludPlus",
                fontSize = 40.sp,
                fontWeight = FontWeight.ExtraBold,
                color = AzulMarino,
                textAlign = TextAlign.Center
            )

            Spacer(modifier = Modifier.height(4.dp))

            // c. Tagline
            Text(
                text = "Tu salud, nuestra prioridad",
                fontSize = 16.sp,
                fontWeight = FontWeight.Normal,
                color = GrisTexto,
                textAlign = TextAlign.Center
            )

            Spacer(modifier = Modifier.height(16.dp))

            // d. Ilustración del médico con degradado vertical en bordes superior e inferior para fundir con el blanco
            Box(
                modifier = Modifier
                    .weight(1f)
                    .fillMaxWidth(),
                contentAlignment = Alignment.Center
            ) {
                Image(
                    painter = painterResource(id = R.drawable.medico_splash),
                    contentDescription = "Médico SaludPlus",
                    modifier = Modifier.fillMaxSize(),
                    contentScale = ContentScale.Fit
                )

                // Degradado superior para fundir bordes
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(48.dp)
                        .align(Alignment.TopCenter)
                        .background(
                            brush = Brush.verticalGradient(
                                colors = listOf(Color.White, Color.White.copy(alpha = 0f))
                            )
                        )
                )

                // Degradado inferior para fundir bordes
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(48.dp)
                        .align(Alignment.BottomCenter)
                        .background(
                            brush = Brush.verticalGradient(
                                colors = listOf(Color.White.copy(alpha = 0f), Color.White)
                            )
                        )
                )
            }

            Spacer(modifier = Modifier.height(16.dp))

            // e. Botón "Comenzar"
            Button(
                onClick = onIrARegistro,
                modifier = Modifier
                    .fillMaxWidth()
                    .height(56.dp),
                shape = RoundedCornerShape(16.dp),
                elevation = ButtonDefaults.buttonElevation(defaultElevation = 0.dp),
                colors = ButtonDefaults.buttonColors(
                    containerColor = AzulPrimario,
                    contentColor = Color.White
                )
            ) {
                Text(
                    text = "Comenzar",
                    fontSize = 18.sp,
                    fontWeight = FontWeight.SemiBold
                )
            }

            Spacer(modifier = Modifier.height(16.dp))

            // f. Botón "Ya tengo una cuenta"
            TextButton(
                onClick = onIrALogin
            ) {
                Text(
                    text = "Ya tengo una cuenta",
                    fontSize = 16.sp,
                    fontWeight = FontWeight.SemiBold,
                    color = AzulPrimario
                )
            }

            Spacer(modifier = Modifier.height(8.dp))
        }
    }
}
