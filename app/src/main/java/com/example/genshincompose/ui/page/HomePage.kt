package com.example.genshincompose.ui.page

import androidx.compose.foundation.*
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.GridCells
import androidx.compose.foundation.lazy.LazyVerticalGrid
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.example.genshincompose.StaggeredVerticalGrid
import com.example.genshincompose.entity.GcDataItem
import com.example.genshincompose.viewmodel.HomeViewModel
import com.google.accompanist.coil.rememberCoilPainter

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun HomePoster(navController: NavController, model: HomeViewModel = viewModel()) {
    model.queryGcData()
    val data: List<GcDataItem> by model.getDataLiveData().observeAsState(listOf())

    LazyVerticalGrid(cells = GridCells.Fixed(2)) {
        items(data) {
            ItemPoster(navController, item = it)
        }
    }

}

@Composable
fun ItemPoster(navController: NavController, item: GcDataItem) {
    Surface(
        modifier = Modifier
            .padding(4.dp),
        color = Color.White,
        elevation = 8.dp,
        shape = RoundedCornerShape(8.dp)
    ) {
        ConstraintLayout() {
            val (image, title, content) = createRefs()

            Image(
                painter = rememberCoilPainter(request = item.url),
                contentDescription = "",
                contentScale = ContentScale.Crop,
                modifier = Modifier
                    .clickable(
                        onClick = {
                        val objectId = item.objectId
                        navController.navigate("detail/$objectId")
                    })
                    .padding(0.dp, 4.dp, 0.dp, 0.dp)
                    .width(180.dp)
                    .height(160.dp)
                    .constrainAs(image) {
                        centerHorizontallyTo(parent)
                        top.linkTo(parent.top)
                    })

            Text(text = item.name,
                color = Color.Black,
                style = MaterialTheme.typography.h6,
                textAlign = TextAlign.Center,
                modifier = Modifier
                    .padding(0.dp, 4.dp, 0.dp, 0.dp)
                    .constrainAs(title) {
                        centerHorizontallyTo(parent)
                        top.linkTo(image.bottom)
                    }
            )
            Text(text = item.from,
                color = Color.Black,
                style = MaterialTheme.typography.body1,
                textAlign = TextAlign.Center,
                modifier = Modifier
                    .padding(4.dp)
                    .constrainAs(content) {
                        centerHorizontallyTo(parent)
                        top.linkTo(title.bottom)

                    })
        }

    }
}
