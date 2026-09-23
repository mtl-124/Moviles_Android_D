package com.lajara.navlab.components

import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Person
import androidx.compose.material3.Icon
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.lajara.navlab.ui.theme.PurpleAccent
import com.lajara.navlab.ui.theme.PurpleLight

@Composable
fun CircleAvatarPlaceholder(
    size: Dp,
    modifier: Modifier = Modifier,
    backgroundColor: Color = PurpleLight,
    iconColor: Color = PurpleAccent,
    borderColor: Color? = null
) {
    val borderModifier = if (borderColor != null) {
        Modifier
            .shadow(4.dp, CircleShape)
            .border(4.dp, borderColor, CircleShape)
    } else {
        Modifier
    }

    Surface(
        modifier = modifier
            .size(size)
            .clip(CircleShape)
            .then(borderModifier),
        shape = CircleShape,
        color = backgroundColor,
        contentColor = iconColor
    ) {
        Box(contentAlignment = Alignment.Center) {
            Icon(
                imageVector = Icons.Default.Person,
                contentDescription = "Avatar",
                modifier = Modifier.size(size * 0.55f),
                tint = iconColor
            )
        }
    }
}
