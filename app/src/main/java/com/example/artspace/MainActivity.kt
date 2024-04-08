package com.example.artspace

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.artspace.ui.theme.ArtSpaceTheme

data class Art(val title: Int, val artist: Int, val imageResource: Int, val imageContentDescription: Int)

val image1 = Art(title = R.string.image_title_bayc_1, artist = R.string.artist, imageResource = R.drawable.bayc_1, imageContentDescription = R.string.content_description_bayc_1 )
val image2 = Art(title = R.string.image_title_bayc_2, artist = R.string.artist, imageResource = R.drawable.bayc_2, imageContentDescription = R.string.content_description_bayc_2 )
val image3 = Art(title = R.string.image_title_bayc_3, artist = R.string.artist, imageResource = R.drawable.bayc_3, imageContentDescription = R.string.content_description_bayc_3 )
val image4 = Art(title = R.string.image_title_bayc_4, artist = R.string.artist, imageResource = R.drawable.bayc_4, imageContentDescription = R.string.content_description_bayc_4 )

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ArtSpaceTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    ArtSpaceApp(modifier = Modifier
                        .fillMaxSize()
                        .wrapContentSize(Alignment.TopCenter)
                        .padding(16.dp)
                    )
                }
            }
        }
    }
}

@Preview
@Composable
fun ArtSpaceApp(modifier: Modifier = Modifier) {
    var currentIndex by remember {
        mutableIntStateOf(1)
    }

    val currentArtPiece = when(currentIndex) {
        1 -> image1
        2 -> image2
        3 -> image3
        else -> image4
    }

    fun onNextPress() {
        if (currentIndex == 4) {
            currentIndex = 1
            return
        }

        currentIndex += 1
    }

    fun onPreviousPress() {
        if (currentIndex == 1) {
            currentIndex = 4
            return
        }

        currentIndex -= 1
    }


    Column(verticalArrangement = Arrangement.SpaceBetween, modifier = modifier) {
        ArtSpaceImage(currentArtPiece)
        ArtSpaceImageDescription(currentArtPiece)
        Spacer(modifier = Modifier.height(32.dp))
        ArtSpaceCTAs({ onNextPress() }, { onPreviousPress() })
    }
}

@Composable
fun ArtSpaceImage(art: Art) {
    Column( verticalArrangement = Arrangement.Center, horizontalAlignment = Alignment.CenterHorizontally, modifier = Modifier.fillMaxHeight(.8f)) {
        Box(modifier = Modifier
            .background(Color.White)
            .shadow(4.dp)
            .clip(RoundedCornerShape(12.dp))
            .padding(32.dp)

            ) {
            Image(
                painter = painterResource(id = art.imageResource),
                contentDescription = stringResource(id = art.imageContentDescription),
                contentScale = ContentScale.FillBounds, modifier = Modifier.fillMaxWidth())
        }
    }
}

@Composable
fun ArtSpaceImageDescription(art: Art) {
    Column(horizontalAlignment = Alignment.CenterHorizontally, modifier = Modifier
        .fillMaxWidth()
        .background(Color.LightGray)
        .padding(8.dp)) {
        Text(text = stringResource(id = art.title))
        Spacer(modifier = Modifier.height(16.dp))
        Text(text = stringResource(id = art.artist))
    }
}

@Composable
fun ArtSpaceCTAs(onNextPress: () -> Unit, onPreviousPress: () -> Unit) {
    Row(horizontalArrangement = Arrangement.SpaceEvenly, modifier = Modifier.fillMaxWidth()) {
        Button(onClick = onPreviousPress, modifier = Modifier.weight(1f)) {
            Text(text = "Previous")
        }
        Spacer(modifier = Modifier.fillMaxWidth(.3f))
        Button(onClick = onNextPress, modifier = Modifier.weight(1f)) {
            Text(text = "Next")
        }
    }
}