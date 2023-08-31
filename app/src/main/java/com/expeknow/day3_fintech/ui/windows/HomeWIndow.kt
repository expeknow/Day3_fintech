package com.expeknow.day3_fintech.ui.windows

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.windowInsetsEndWidth
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.BottomSheetScaffold
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.SelectableChipColors
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Notifications
import androidx.compose.material.icons.rounded.Search
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Divider
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FilterChip
import androidx.compose.material3.FilterChipDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.TileMode
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.modifier.modifierLocalConsumer
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.expeknow.day3_fintech.R
import com.expeknow.day3_fintech.ui.utils.ProfileClass


@OptIn(ExperimentalMaterialApi::class)
@Composable
fun HomeWindow() {

    BottomSheetScaffold(
        sheetContent = { SheetContens(Modifier.padding(20.dp))},
        sheetPeekHeight = 420.dp,
        sheetShape = RoundedCornerShape(40.dp, 40.dp, 0.dp, 0.dp),
        backgroundColor = colorResource(id = R.color.primary_green_background)
    ) {
        Column {
            TopBar()

            ChipsRow()

            MainAmount(2542.00f)
        }
    }
}


@Composable
fun TopBar() {
    Row(modifier = Modifier.padding(horizontal = 20.dp, vertical = 20.dp),
        verticalAlignment = Alignment.CenterVertically) {
        Image(painter = painterResource(id = R.drawable.profile2),
            contentDescription = "logo",
            modifier = Modifier
                .size(50.dp)
                .clip(RoundedCornerShape(50.dp)))

        Column(
            Modifier
                .padding(horizontal = 10.dp)
                .weight(1f), verticalArrangement = Arrangement.Center) {
            Text(text = "Good Morning,", fontSize = 14.sp, fontWeight = FontWeight.Normal,
                modifier = Modifier.padding(horizontal = 10.dp), color = Color.White)
            Text(text = "Atul Kumar 👋", fontSize = 18.sp, fontWeight = FontWeight.Bold,
                modifier = Modifier.padding(horizontal = 10.dp), color = Color.White)
        }


        Button(onClick = { /*TODO*/ },
            shape = RoundedCornerShape(50.dp),
            contentPadding = PaddingValues(0.dp),
            colors = ButtonDefaults.buttonColors(
                containerColor = colorResource(id = R.color.alert_button_bg)
            ), modifier = Modifier.size(50.dp)
        ) {
            Icon(imageVector = Icons.Filled.Notifications,
                contentDescription = "",
                Modifier.size(30.dp))
        }

    }
}

@Composable
fun ChipsRow() {
    val tabsItem = listOf(
        "Earnings",
        "Off-Cycle",
        "Analytics",
        "Cards"
    )

    LazyRow(Modifier.padding(horizontal = 10.dp)) {
        items(tabsItem.size){
            val tabName = tabsItem[it]
            Chips(tabName)
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun Chips(tabName: String) {
    val state = remember { mutableStateOf(true) }
    FilterChip(
        selected = state.value,
        onClick = { state.value = !state.value },
        shape = RoundedCornerShape(20.dp),
        label = { Text(text = tabName, fontWeight = FontWeight.Bold, fontSize = 16.sp)},
        modifier = Modifier.padding(horizontal = 5.dp, vertical = 5.dp),
        colors = FilterChipDefaults.filterChipColors(
            disabledContainerColor = Color.Transparent,
            selectedContainerColor = Color.White,
            selectedLabelColor = Color.Black,
            labelColor = Color(0xFFCFF1F3),
            containerColor = Color.Transparent
        ),
        border = FilterChipDefaults.filterChipBorder(
            borderColor = Color.Transparent
        )

    )
}


@Composable
fun MainAmount(amount: Float) {
    Column(
        Modifier
            .fillMaxWidth()
            .padding(vertical = 40.dp), horizontalAlignment = Alignment.CenterHorizontally,) {
        Text(text = "Wage Earnings", fontSize = 20.sp, fontWeight = FontWeight.Normal,
        color = colorResource(id = R.color.light_gray_cyan_text_color))
        Text(text = "$$amount", fontSize = 50.sp, fontWeight = FontWeight.Bold
            ,
        color = Color.White)

    }
}

@Composable
fun SheetContens(modifier: Modifier = Modifier) {

    Column(
        Modifier
            .fillMaxSize()
            ) {
        Row(modifier = modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.Center,
           ) {
            Divider(color = Color.LightGray, thickness = 4.dp,
                modifier = Modifier.padding(horizontal = 130.dp))
        }

         Text(text = "Recent Send", fontSize = 20.sp, fontWeight = FontWeight.Bold,
             modifier = Modifier.padding(horizontal = 20.dp, vertical = 5.dp))

        RecentContactsRowItems(modifier)

        Text(text = "Your History", fontSize = 20.sp, fontWeight = FontWeight.Bold,
            modifier = Modifier.padding(horizontal = 20.dp))

        TransectionSearchBar(modifier)

        Column(Modifier.verticalScroll(rememberScrollState())) {
            repeat(5){
                TransectionHistoryRowItem(name = "Atul", date = "May 6, 2023",
                    time = "4:50PM", transferAmount = 580.00f, isSent = true)
                TransectionHistoryRowItem(name = "Atul", date = "May 7, 2023",
                    time = "2:50PM", transferAmount = 210.00f, isSent = false)
            }
        }


    }
}

@Composable
fun RecentContactsRowItems(modifier: Modifier) {

    val profileList = listOf(
        ProfileClass(R.drawable.p1, "Johnny"),
        ProfileClass(R.drawable.p2, "Alex"),
        ProfileClass(R.drawable.p3, "Jason"),
        ProfileClass(R.drawable.p4, "Scarllet"),
        ProfileClass(R.drawable.p5, "Marry")
    )

     LazyRow {

        items(profileList.size){
            val profile = profileList[it]
            Column(horizontalAlignment = Alignment.CenterHorizontally,
                modifier = Modifier.padding(vertical = 15
                    .dp)) {
                Card(shape = RoundedCornerShape(50.dp),
                    modifier = Modifier.size(50.dp)) {
                    Image(painter = painterResource(id = profile.imageId), contentDescription = "",
                        contentScale = ContentScale.Fit, alignment = Alignment.Center)
                }
                Text(text = profile.name, fontSize = 13.sp, fontWeight = FontWeight.Light,
                    modifier = Modifier.padding(horizontal = 20.dp))
            }
        }

    }
}


@Composable
fun TransectionSearchBar(modifier: Modifier) {
    Row(
        Modifier
            .fillMaxWidth()
            .padding(horizontal = 10.dp, vertical = 10.dp), verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.Center) {
        Card(modifier = Modifier.weight(1f),  shape = RoundedCornerShape(45.dp),
            colors = CardDefaults.cardColors(
                containerColor = Color.White
            ),
            border = BorderStroke(1.dp, Color.LightGray),
            )
        {
            Row(modifier = modifier, verticalAlignment = Alignment.CenterVertically) {
                Icon(imageVector = Icons.Rounded.Search, contentDescription = "search",
                    Modifier.size(25.dp))
                Text(text = "Search transection", color = Color.LightGray, fontSize = 16.sp,
                    modifier = Modifier.padding(horizontal = 5.dp))
            }
        }
        Icon(painter = painterResource(id = R.drawable.filter), contentDescription = "",
            modifier = modifier.size(30.dp))
    }
}

@Composable
fun TransectionHistoryRowItem(name: String, date: String, time: String, transferAmount: Float,
                              isSent: Boolean){
    Row(Modifier.padding(horizontal = 10.dp), verticalAlignment = Alignment.CenterVertically) {
        Card(shape = RoundedCornerShape(100.dp),
            colors = CardDefaults.cardColors(
                containerColor = colorResource(id = if(isSent) R.color.outgoing_icon_bg else R.color.incoming_icon_bg)
            ), modifier = Modifier.padding(10.dp)
        ) {
            Icon(painter = painterResource(id = if(isSent) R.drawable.outgoing else R.drawable.incoming),
                contentDescription = "icon indicating direction of transfer",
                modifier = Modifier.padding(15.dp),
                tint = Color.White)
        }

        val nameText = if(isSent) "To" else "From"
        Column (Modifier.weight(1f)) {
            Text(text = "$nameText $name", fontSize = 16.sp, fontWeight = FontWeight.ExtraBold)
            Text(text = "$time • $date", fontSize = 13.sp, fontWeight = FontWeight.Normal,
                color = Color.Gray)
        }
        val sign = if (isSent) "-" else "+"
        val transferOrIncoming = if(isSent) "Transfer Out" else "Incoming"
        val textTine = if(isSent) colorResource(id = R.color.outgoing_icon_bg) else colorResource(id = R.color.incoming_icon_bg)
        Column(verticalArrangement = Arrangement.Center, modifier = Modifier.padding(20.dp)) {
            Text(text = "${sign}$${transferAmount}", fontSize = 18.sp,
                fontWeight = FontWeight.Bold, color = textTine)
            Text(text = transferOrIncoming, fontSize = 12.sp, fontWeight = FontWeight.Light,
                color = Color.Gray)
        }

    }
}



fun createGradientBrush(
    colors: List<Color>,
    isVertical: Boolean = true
): Brush {
    val endOffset = if (isVertical) {
        Offset(0f, Float.POSITIVE_INFINITY)
    } else {
        Offset(Float.POSITIVE_INFINITY, 0f)
    }

    return Brush.linearGradient(
        colors = colors,
        start = Offset(0f, 0f),
        end = endOffset,
        tileMode = TileMode.Clamp
    )
}

@Preview(showBackground = true)
@Composable
fun Preview() {
    HomeWindow()
}