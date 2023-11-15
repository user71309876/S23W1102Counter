package kr.ac.kumoh.ce.s20180474.s23w1102counter

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.ViewModelProvider
import kr.ac.kumoh.ce.s20180474.s23w1102counter.ui.theme.S23W1102CounterTheme

class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        val vm = ViewModelProvider(this)[CounterViewModel::class.java]
        val vm1 = ViewModelProvider(this)[CounterViewModel::class.java]
        super.onCreate(savedInstanceState)
        setContent {
            MyApp{
                Column(
                    modifier= Modifier
                        .fillMaxSize()
                        .padding(8.dp),
                    verticalArrangement = Arrangement.Center
                ) {
                    Clicker()
                    Counter(vm)
                    Counter(vm1)
                    //view model은 싱글턴 객체이므로 같이 증가한다
                    //따로 증가시킬려면 따로 viewModel class를 만들어야 함
                }
            }
        }
    }
}

@Composable
fun MyApp(content: @Composable ()-> Unit) {//Composable 인자를 받을건데 반환값은 없음
    // A surface container using the 'background' color from the theme
    Surface(
        modifier = Modifier.fillMaxSize(),
        color = MaterialTheme.colorScheme.background
    ) {
        content()

    }
}

@Composable
fun Clicker(){
//    var txtString="눌러주세요"
//    var txtString by remember { mutableStateOf("눌러주세요") }
    val (txtString,setTxtString)= remember {
        mutableStateOf("눌러주세요")
    }//useState???
    Column(modifier = Modifier
        .fillMaxWidth()
        .padding(8.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally) {
        Text(text = txtString,
            fontSize = 70.sp,
        )
        Button(modifier = Modifier
            .fillMaxWidth(),
            onClick = {
                setTxtString("눌렸습니다")
            }) {
            // Text(text = "눌러봐")
            Text("눌러봐")
        }
    }
}

@Composable
fun Counter(vm:CounterViewModel){
//    val (txtInteger,setTxtInteger)= rememberSaveable {
//        mutableStateOf(0)
//    }//useState???
    val count by vm.count.observeAsState(0)
    Column(modifier = Modifier
        .fillMaxWidth()
        .padding(8.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally) {
        Text(text = "$count",
            fontSize = 70.sp,
        )
        Row(
        ){
            Button(
                modifier = Modifier.weight(1f),
                onClick = {
                    vm.onAdd()
                }
            ) { Text("증가") }
            Spacer(modifier=Modifier.width(8.dp))
            Button(
                modifier = Modifier.weight(1f),
                onClick = {
                    if(count>0)vm.onSub()
                }
            ) { Text("감소") }
        }
    }
}

@Composable
fun Greeting(name: String, modifier: Modifier = Modifier) {
    Text(
        text = "Hello $name!",
        modifier = modifier
    )
}