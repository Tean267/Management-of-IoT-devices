

let Listbox = document.querySelectorAll('.khoi-size');
let box1 = Listbox[0];
let box2 = Listbox[1];
let box3 = Listbox[2];
let box4 = Listbox[3];
// let arrayP = box.getElementsByTagName("p");   
// console.log(arrayP[1].innerText);

let countTemp = document.getElementsByClassName(".count1").innerText
let countHum = document.getElementsByClassName(".count2").innerText
let countLight = document.getElementsByClassName(".count3").innerText
let countMua = document.getElementsByClassName(".count4").innerText
let count =0;

function changeTemp(){
    if(countTemp<=10){
        box1.style.backgroundColor = "rgb(220 88 66)";
    }
    else if(countTemp<=25){
        box1.style.backgroundColor = "rgb(243 88 79)";
    }
    else if(countTemp<=35){
        box1.style.backgroundColor = "rgb(243 67 56)";
    }
    else {
        box1.style.backgroundColor ="rgb(247 33 20)";
    }
    // if(countTemp>50){
    //     box1.style.backgroundColor ="#fd0606"
    // }
}
function changeHum(){
    if(countHum<=10){
        box2.style.backgroundColor = "rgb(112 226 154)";
    }
    else if(countHum<=25){
        box2.style.backgroundColor = "rgb(43 188 97)";
    }
    else if(countHum<=35){
        box2.style.backgroundColor = "rgb(34 228 106)";
    }
    else {
        box2.style.backgroundColor ="rgb(5 204 79)";
    }
    // if(countHum>50){
    //     box2.style.backgroundColor ="#fd0606"
    // }
}
function changeMua(){
    if(countMua<=10){
        box4.style.backgroundColor = "#ad82d6";
    }
    else if(countMua<=25){
        box4.style.backgroundColor = "hsl(271, 62%, 62%)";
    }
    else if(countMua<=35){
        box4.style.backgroundColor = "hsl(271, 69%, 58%)";
    }
    else {
        box4.style.backgroundColor ="hsl(271, 83%, 47%)";
    }
    // if(countTemp>50){
    //     box1.style.backgroundColor ="#fd0606"
    // }
}
function changeLight(){
    if(countLight<=10){
        box3.style.backgroundColor = "rgb(226 217 103)";
    }
    else if(countLight<=25){
        box3.style.backgroundColor = "rgb(223 218 58)";
    }
    else if(countLight<=35){
        box3.style.backgroundColor = "rgb(235 227 33)";
    }
    else {
        box3.style.backgroundColor ="rgb(235 226 3)";
    }
    // if(countLight>50){
    //     box3.style.backgroundColor ="#fd0606"
    // }
}
// setInterval(changeTemp,1000)

 //lấy database   
// function getTempNew(){
//     fetch('http://localhost:9000/Iot/getTempNew')
//     .then(function(response){
//         return response.json();
//     })
//     .then(function(posts){
//         document.querySelector(".count1").innerText = posts;
//         console.log(posts);
//     })
//     .catch(function(err){
//         console.log(err);
//     });
// }   
    // setInterval(getTempNew,1000)
    function getTempAllData(){
        fetch('http://localhost:9000/Iot/getAllData')
        .then(function(response){
            return response.json();
        })
        .then(function(posts){
            const temp = []
            const hum = []
            const light = []
            const mua =[]
            const time =[]
            for(let key of posts){
                temp.push(key.temperature)
                hum.push(key.humidity)
                light.push(key.light)
                mua.push(key.mua)
                time.push(key.time)
                
            }
            // hiển thị nhiệt độ
           countTemp = document.querySelector(".count1").innerText = temp[temp.length-1];
           countHum = document.querySelector(".count2").innerText = hum[hum.length-1];
            countLight = document.querySelector(".count3").innerText = light[light.length-1];
            countMua = document.querySelector(".count4").innerText = mua[mua.length-1];
            changeTemp();
            changeHum();
            changeLight();
            changeMua();
            //hiển thị đồ thị
            var ctx = document.getElementById('myChart').getContext('2d');

            // Định nghĩa dữ liệu cho biểu đồ
            var ctx2 = document.getElementById('myChart2').getContext('2d');

// Định nghĩa dữ liệu cho biểu đồ
var data = {
    labels: time,
    datasets: [
        {
            label: 'Nhiệt độ',
            data: temp,
            yAxisID: 'left-y-axis',
            borderColor: 'red',
            backgroundColor: 'transparent',
        },
        {
            label: 'Độ ẩm',
            data: hum,
            yAxisID: 'left-y-axis',
            borderColor: 'rgb(43 188 97)',
            backgroundColor: 'transparent',
        },
        {
            label: 'Ánh sáng',
            data: light,
            yAxisID: 'right-y-axis',
            borderColor: 'rgb(226 217 103)',
            backgroundColor: 'transparent',
        },

       
    ],
};

// Định nghĩa tùy chọn cho biểu đồ
var options = {
    scales: {
        yAxes: [
            {
                id: 'left-y-axis',
                position: 'left',
                ticks: {
                    min: 0, // Giá trị tối thiểu trên trục tung
                     max: 100, // Giá trị tối đa trên trục tung
                      stepSize: 5, // Bước giữa các giá trị
                    
                },
            },
            {
                id: 'right-y-axis',
                position: 'right',
                ticks: {
                    min: 0, // Giá trị tối thiểu trên trục tung
                     max: 2000, // Giá trị tối đa trên trục tung
                    stepSize: 100, // Bước giữa các giá trị
                    
                },
            },
        ],
        xAxes: [
            {
                type: 'category',
            },
        ],
    },
};

// Tạo biểu đồ
var myChart = new Chart(ctx, {
    type: 'line',
    data: data,
    options: options,
});

////////////////////////////////////////////////////////////////
var data1 = {
    labels: time,
    datasets: [
        {
            label: 'Độ mưa',
            data: mua,
            yAxisID: 'left-y-axis',
            borderColor: 'red',
            backgroundColor: 'transparent',
        },
   
    ],
};

// Định nghĩa tùy chọn cho biểu đồ
var options1 = {
    scales: {
        yAxes: [
            {
                id: 'left-y-axis',
                position: 'left',
                ticks: {
                    min: 0, // Giá trị tối thiểu trên trục tung
                     max: 100, // Giá trị tối đa trên trục tung
                      stepSize: 5, // Bước giữa các giá trị
                    
                },
            },
           
        ],
        xAxes: [
            {
                type: 'category',
            },
        ],
    },
};

// Tạo biểu đồ
var myChart2 = new Chart(ctx2, {
    type: 'line',
    data: data1,
    options: options1,
});
           
        })
        .catch(function(err){
            console.log(err);
        });
    }   
    // getTempAllData()
    console.log(1)
    setInterval(getTempAllData,5000)
// const AllTempData = []

//     fetch('http://localhost:9000/Iot/getAllTemp')
//     .then(function(response){
//         return response.json();
//     })
//     .then(function(posts){  
//         // posts.forEach(element => {
//         //     console.log(element)
//         // });   
//              AllTempData.push(...posts);
//              return AllTempData;
          
//     })
//     .then(function(AllTempData){
//         console.log(AllTempData[0])
//     })
//     .catch(function(err){
//         console.log(err);
//     });

// console.log(AllTempData[0])

// function getAllTempData() {
//     return fetch('http://localhost:9000/Iot/getAllTemp')
//     .then(function(response){
//         if (!response.ok) {
//             throw new Error('Network response was not ok');
//         }
//         return response.json();
//     })
//     .catch(function(err){
//         console.log(err);
//         return []; // Trả về một mảng rỗng trong trường hợp lỗi
//     });
// }
// const AllTempData = []
// getAllTempData()
//     .then(function(data) {
//         AllTempData.push(...data);
//     })
//     .catch(function(error) {
//         console.log(error);
//     });


function OnFan(){
    const blade1 = document.querySelector(".blade-1").style.animation="rotateFan1 0.5s linear infinite";
    const blade2 = document.querySelector(".blade-2").style.animation="rotateFan2 0.5s linear infinite";
    const blade3 = document.querySelector(".blade-3").style.animation="rotateFan3 0.5s linear infinite";
}
const OnLampRight = document.querySelector(".onLampRight").addEventListener("click",function(e){
    const postData = "room/lamp,onlampright";
    
    // Tùy chọn của yêu cầu Fetch
    const requestOptions = {
        method: "POST",                   // Phương thức POST
        headers: {
            "Content-Type": "application/text"  // Kiểu dữ liệu của nội dung là JSON
        },
        body: JSON.stringify(postData)     // Chuyển đổi đối tượng postData thành chuỗi JSON
    };
    
    // Gọi API sử dụng Fetch
    fetch("http://localhost:9000/Iot/controlLamp", requestOptions)
        .then(response => {
            if (!response.ok) {
                throw new Error(`Lỗi khi gọi API: ${response.status}`);
            }
            return response.text;  // Trả về dữ liệu JSON từ phản hồi
        })
        .then(data => {
          
            OnFan();
            // Xử lý dữ liệu từ API tại đây
        })
        .catch(error => {
            console.error("Lỗi: ", error);
        });
})


function OffFan(){
    const blade1 = document.querySelector(".blade-1").style.removeProperty("animation");
    const blade2 = document.querySelector(".blade-2").style.removeProperty("animation");
    const blade3 = document.querySelector(".blade-3").style.removeProperty("animation");
   
}
const OffLampRight = document.querySelector(".offLampRight").addEventListener("click",function(e){
    const postData = "room/lamp,offlampright";
    
    // Tùy chọn của yêu cầu Fetch
    const requestOptions = {
        method: "POST",                   // Phương thức POST
        headers: {
            "Content-Type": "application/text"  // Kiểu dữ liệu của nội dung là JSON
        },
        body: JSON.stringify(postData)     // Chuyển đổi đối tượng postData thành chuỗi JSON
    };
    
    // Gọi API sử dụng Fetch
    fetch("http://localhost:9000/Iot/controlLamp", requestOptions)
        .then(response => {
            if (!response.ok) {
                throw new Error(`Lỗi khi gọi API: ${response.status}`);
            }
            return response.text;  // Trả về dữ liệu JSON từ phản hồi
        })
        .then(data => {
            console.log(data)
            OffFan()
            // Xử lý dữ liệu từ API tại đây
        })
        .catch(error => {
            console.error("Lỗi: ", error);
        });
})


function OnLight(){
    const light = document.querySelector(".light-bulb").style.animation="bulbLight 1s linear forwards";
}
const OnLampLeft = document.querySelector(".onLampLeft").addEventListener("click",function(e){
    const postData = "room/lamp,onlampleft";
    
    // Tùy chọn của yêu cầu Fetch
    const requestOptions = {
        method: "POST",                   // Phương thức POST
        headers: {
            "Content-Type": "application/text"  // Kiểu dữ liệu của nội dung là JSON
        },
        body: JSON.stringify(postData)     // Chuyển đổi đối tượng postData thành chuỗi JSON
    };
    
    // Gọi API sử dụng Fetch
    fetch("http://localhost:9000/Iot/controlLamp", requestOptions)
        .then(response => {
            if (!response.ok) {
                throw new Error(`Lỗi khi gọi API: ${response.status}`);
            }
            return response.text;  // Trả về dữ liệu JSON từ phản hồi
        })
        .then(data => {
            console.log(data)
            OnLight();
            // Xử lý dữ liệu từ API tại đây
        })
        .catch(error => {
            console.error("Lỗi: ", error);
        });
})



function OffLight(){
    const light = document.querySelector(".light-bulb").style.removeProperty("animation");
}
const OffLampLeft = document.querySelector(".offLampLeft").addEventListener("click",function(e){
    const postData = "room/lamp,offlampleft";
    
    // Tùy chọn của yêu cầu Fetch
    const requestOptions = {
        method: "POST",                   // Phương thức POST
        headers: {
            "Content-Type": "application/text"  // Kiểu dữ liệu của nội dung là JSON
        },
        body: JSON.stringify(postData)     // Chuyển đổi đối tượng postData thành chuỗi JSON
    };
    
    // Gọi API sử dụng Fetch
    fetch("http://localhost:9000/Iot/controlLamp", requestOptions)
        .then(response => {
            if (!response.ok) {
                throw new Error(`Lỗi khi gọi API: ${response.status}`);
            }
            return response.text;  // Trả về dữ liệu JSON từ phản hồi
        })
        .then(data => {
            console.log(data)
            OffLight();
            // Xử lý dữ liệu từ API tại đây
        })
        .catch(error => {
            console.error("Lỗi: ", error);
        });
})

const OnLampTest = document.querySelector(".onLamptest").addEventListener("click",function(e){
    const postData = "room/lamp,onlamptest";
    
    // Tùy chọn của yêu cầu Fetch
    const requestOptions = {
        method: "POST",                   // Phương thức POST
        headers: {
            "Content-Type": "application/text"  // Kiểu dữ liệu của nội dung là JSON
        },
        body: JSON.stringify(postData)     // Chuyển đổi đối tượng postData thành chuỗi JSON
    };
    
    // Gọi API sử dụng Fetch
    fetch("http://localhost:9000/Iot/controlLamp", requestOptions)
        .then(response => {
            if (!response.ok) {
                throw new Error(`Lỗi khi gọi API: ${response.status}`);
            }
            return response.text;  // Trả về dữ liệu JSON từ phản hồi
        })
        .then(data => {
            console.log(data)
            // Xử lý dữ liệu từ API tại đây
        })
        .catch(error => {
            console.error("Lỗi: ", error);
        });
})


const OffLampTest = document.querySelector(".offLamptest").addEventListener("click",function(e){
    const postData = "room/lamp,offlamptest";
    
    // Tùy chọn của yêu cầu Fetch
    const requestOptions = {
        method: "POST",                   // Phương thức POST
        headers: {
            "Content-Type": "application/text"  // Kiểu dữ liệu của nội dung là JSON
        },
        body: JSON.stringify(postData)     // Chuyển đổi đối tượng postData thành chuỗi JSON
    };
    
    // Gọi API sử dụng Fetch
    fetch("http://localhost:9000/Iot/controlLamp", requestOptions)
        .then(response => {
            if (!response.ok) {
                throw new Error(`Lỗi khi gọi API: ${response.status}`);
            }
            return response.text;  // Trả về dữ liệu JSON từ phản hồi
        })
        .then(data => {
            console.log(data)
            OffTest();
            // Xử lý dữ liệu từ API tại đây
        })
        .catch(error => {
            console.error("Lỗi: ", error);
        });
})

