

let array = [];
belongGroupX.forEach((value) => array.push(value))
let nums = [];

array.forEach(value => {
    nums.push(value.groupNo.toString());
})

if(nums.indexOf(groupNo.toString()) === -1 && groupNo.toString() !== "0" && groupNo.toString() !== "-1"){
    alert("자신이 가입한 그룹으로만 갈 수 있습니다!");
    location.href="/main/calendar?groupNo=0";
}


window.onload = () => {
    let weatherIcon = {
        '01' : 'fas fa-sun',
        '02' : 'fas fa-sun',
        '03' : 'fas fa-sun',
        '04' : 'fas fa-sun',
        '09' : 'fas fa-cloud-sun-rain',
        '10' : 'fas fa-cloud-showers-heavy',
        '11' : 'fas fa-cloud-bolt',
        '13' : 'fas fa-snowflake',
        '50' : 'fas fa-smog',
    }

    $.ajax({
        url: 'http://api.openweathermap.org/data/2.5/weather?q=Daegu&appid=c7d08555216a4a02b0fe0c27b48b8060&units=metric&lang=ko',
        type: 'get',
        dataType: 'json',
        // async: false,
        success: function(data) {
            var Icon = (data.weather[0].icon).substr(0,2);
            var Temp = Math.floor(data.main.temp) + '°';
            var city = data.name;

            let date = new Date();

            $('.CurrIcon').append('<i class="'+ weatherIcon[Icon] + '"></i>');
            $('.CurrTemp').prepend(Temp);
            $('.City').append(city);
            $('.y').append(date.getFullYear() + '년');
            $('.m').append(date.getMonth() + 1 + '월');
            $('.d').append(date.getDate() + '일');
        }
    })
}


document.addEventListener('DOMContentLoaded', function() {

    var calendarEl = document.getElementById('calendar');


    calendarObect = {
        headerToolbar: {
            left: 'prev,next,today',
            center: 'title',
            right: ''
        },
        // initialDate: '2023-01-12',
        navLinks: true, // can click day/week names to navigate views
        selectable: true,
        selectMirror: true,
        editable: true,
        locale: 'ko',
        aspectRatio: 1.35,
        select: function (arg) {
            var title = prompt('일정 이름을 입력해주세요');
            var color = document.querySelector('input[name="color"]:checked').value;
            if (title) {
                console.log(arg.start);
                console.log(arg.end);
                let obj = {
                    title: title,
                    start: arg.start,
                    end: arg.end,
                    allDay: arg.allDay,
                    groupNo: groupNo,
                    color : color
                }

                save_data(obj);
                // calendar.addEvent({
                //
                //     title: title,
                //     start: arg.start,
                //     end: arg.end,
                //     allDay: arg.allDay
                // })
            }
            calendar.unselect()
        },
        eventClick: function (arg) {
            if (confirm('정말로 해당 일정을 삭제 하시겠습니까?')) {
                arg.event.remove() // 여기서 리무브를 시키면 안되고 db에 삭제를 시켜야 한다.
                let obj = {
                    title: arg.event._def.title,
                    start: arg.event._instance.range.start
                };

                delete_data(obj);
            }
            console.log(arg.event);
        },

        eventDrop: function (info) {
            //드래그 수정
            let obj = {
                // 이건 수정된 정보
                title: info.event._def.title,
                start: info.event._instance.range.start,
                end: info.event._instance.range.end
            }

            let oldObj = {
                title: info.oldEvent._def.title,
                start: info.oldEvent._instance.range.start,
                end: info.oldEvent._instance.range.end
            }
            // console.log(info)
            // console.log(obj)
            // console.log(oldObj)
            update_data(obj, oldObj);

        },
        dayMaxEvents: true, // allow "more" link when too many events
        events:
            function (info, successCallback, failureCallback) {
                $.ajax({
                    url: '/set',
                    type: 'post',
                    data: {groupNo: groupNo},
                    dataType: 'json',
                    success: function (data) {
                        successCallback(data);
                    },
                    error: function (error) {
                        console.log("set" + error)
                    }
                })
            }
    }



    calendar = new FullCalendar.Calendar(calendarEl, calendarObect);
    calendar.render();

    const title = document.getElementById('fc-dom-1');

    title.onclick = () => {
        direct_group(0);
    }
});

    function save_data(obj) {
        console.log("개인");
        $.ajax({
            url: '/save',
            type: 'post',
            data: JSON.stringify(obj),
            dataType: 'json',
            contentType: "application/json",
            success: function (data) {
                console.log("save_data - suc")
            },
            error: function (error) {
                console.log("save_data - err")
            }
        })
        location.reload();
    }

    function delete_data(obj) {
        $.ajax({
            type: 'post',
            url: '/delete',
            data: JSON.stringify(obj),
            dataType: 'json',
            contentType: "application/json",
            success: function (data) {

            },
            error: function (error) {
                console.log("delete" + error)
            }
        })
        location.reload();
    }

    function update_data(obj, oldObj) {
        console.log(obj)
        console.log(oldObj)
        let objList = [obj, oldObj]
        $.ajax({
            url: "/update",
            type: 'patch',
            data: JSON.stringify(objList),
            dataType: 'json',
            contentType: "application/json",
            success: function (data) {

            },
            error: function (error) {
                console.log("update" + error)
            }
        })
    }






    const profileInputForm = document.getElementById('profile-input-form');
    const profileInput = profileInputForm.querySelector('input')


    profileInput.onchange = () => {
        if (profileInput.value !== "") {
            profileInputForm.submit();
        }
    }

    function change_image() {
        profileInput.click();
    }

    //form 생성, url 등록 후 form 반환
    function postFormCreate(url){
        const form = document.createElement('form');
        form.setAttribute('method', 'post');
        form.setAttribute('action', url);
        return form;
    }
    function getFormCreate(url){
        const form = document.createElement('form');
        form.setAttribute('method', 'get');
        form.setAttribute('action', url);
        return form;
    }

    //input 만들어서 name, value 지정 후 input 반환
    function inputCreate(name, value){

        const data = document.createElement('input');
        data.setAttribute('type', 'hidden'); // type = hidden
        data.setAttribute('name', name);
        data.setAttribute('value', value);

        return data;
    }

    //form body에 붙이고 submit
    function formSubmit(form){
        document.body.appendChild(form);
        form.submit();
    }


    function group_create() {
        let groupName = prompt("생성할 그룹 이름을 적어주세요");

        if (groupName.trim() === null) {
            alert("그룹 이름을 제대로 입력해주세요!")
        } else {
            const form = postFormCreate("/group/create");
            const data_1 = inputCreate("groupName", groupName);
            form.appendChild(data_1);
            formSubmit(form)
        }
    }

    function direct_group(groupNo) {

        console.log("main - direct_group_groupNo : " + groupNo)
        const form = getFormCreate("/main/calendar");
        const data = inputCreate('groupNo',groupNo);
        form.appendChild(data);
        formSubmit(form);
    }

    function group_secession(groupNo) {
        if (groupNo === "0" || groupNo === "-1") {
            alert("그룹을 선택해주세요");
            location.reload();
        } else {
            const form = postFormCreate("/group/secession");
            const data = inputCreate('groupNo',groupNo);
            form.appendChild(data);
            formSubmit(form);
        }

    }

    function group_invite(groupNo) {
        if (groupNo === "0" || groupNo === "-1") {
            alert("그룹을 선택해주세요");
            location.reload();
        } else {
            let inviteid = prompt("초대할 사람의 id를 입력해주세요")

            const form = postFormCreate("/user/invite");
            const data_1 = inputCreate('id',inviteid);
            const data_2 = inputCreate('groupNo',groupNo);
            form.appendChild(data_1);
            form.appendChild(data_2);
            formSubmit(form);
        }

    }

    function dateFormat(date) {
        let month = date.getMonth() + 1;
        let day = date.getDate();
        let hour = date.getHours();
        let minute = date.getMinutes();
        let second = date.getSeconds();

        month = month >= 10 ? month : '0' + month;
        day = day >= 10 ? day : '0' + day;
        hour = hour >= 10 ? hour : '0' + hour;
        minute = minute >= 10 ? minute : '0' + minute;
        second = second >= 10 ? second : '0' + second;

        return date.getFullYear() + '-' + month + '-' + day;
    }
