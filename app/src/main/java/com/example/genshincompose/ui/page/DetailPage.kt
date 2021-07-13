package com.example.genshincompose

import android.util.Log
import androidx.compose.foundation.*
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.genshincompose.entity.GcDataItem
import com.example.genshincompose.viewmodel.HomeViewModel
import com.google.accompanist.coil.rememberCoilPainter

private const val TAG = "DetailPage"

@Composable
fun DetailPoster(objectId: String?, model: HomeViewModel = viewModel(), pressOnBack: () -> Unit) {
    Log.d(TAG, "DetailPoster: $objectId")
    model.queryGcDetailById(objectId)
    val item: GcDataItem? by model.getDetailLiveData().observeAsState()
    Log.d(TAG, "DetailPoster: 111$item ")
    item?.let {
        Column(
            modifier = Modifier
                .verticalScroll(rememberScrollState())
                .background(MaterialTheme.colors.background)
                .fillMaxHeight()
        ) {
            ConstraintLayout {
                val (arrow, image, title, content) = createRefs()
                Image(painter = rememberCoilPainter(request = item?.detailImgUrl),
                    contentDescription = "",
                    contentScale = ContentScale.Crop,
                    modifier = Modifier
                        .height(300.dp)
                        .constrainAs(image) {
                            centerHorizontallyTo(parent)
                            top.linkTo(parent.top)
                        })

                Text(
                    text = item?.name!!,
                    style = MaterialTheme.typography.h5,
                    overflow = TextOverflow.Ellipsis,
                    maxLines = 1,
                    modifier = Modifier
                        .constrainAs(title) {
                            top.linkTo(image.bottom)
                        }
                        .padding(start = 16.dp, top = 16.dp)
                )
                Text(
                    text = item?.description!!,
                    style = MaterialTheme.typography.body2,
                    modifier = Modifier
                        .constrainAs(content) {
                            top.linkTo(title.bottom)
                        }
                        .padding(16.dp)
                )

                Icon(
                    imageVector = Icons.Filled.ArrowBack,
                    tint = Color.White,
                    contentDescription = null,
                    modifier = Modifier
                        .constrainAs(arrow) {
                            top.linkTo(parent.top)
                        }
                        .padding(12.dp)
                        .clickable(onClick = { pressOnBack() })
                )
            }
        }
    }


}

