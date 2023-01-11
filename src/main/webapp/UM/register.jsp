<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>회원가입</title>
    <link rel="stylesheet" href="../assets/css/reset.css">
    <link rel="stylesheet" href="../assets/css/footer.css">
    <link rel="stylesheet" href="../assets/css/common.css">
    <link rel="stylesheet" href="../assets/css/register.css">
    <script src="https://spi.maps.daum.net/imap/map_js_init/postcode.v2.js"></script>
    <script src="../assets/js/jquery-3.6.1.js"></script>
    <script src="../assets/js/register.js" defer></script>
    <script>
        document.addEventListener("DOMContentLoaded", function () {
            const NickBtn = document.getElementById("dblCheck2");
            NickBtn.addEventListener('click', function () {
                const form = document.getElementById("list_sh")
                let nickname = form['nickName'].value
                $.getJSON('/checkingDuplicate', {"nickname": nickname}, function (data) {
                    console.log(data, form['nickName'].value)
                    switch (data['result']) {
                        case "success":
                            alert("사용하실 수 있는 별명입니다.")
                            break;
                        default:
                            alert("중복된 별명입니다, 다른 별명을 입력하여 주십시오.")
                            break;
                    }
                })
            })
        })
    </script>
    <script>
        document.addEventListener("DOMContentLoaded", function () {
            const AuthBtn = document.getElementById("dblCheck1");
            AuthBtn.addEventListener('click', function () {
                console.log("popo")
                const form = document.getElementById("list_sh")
                let email = $("#id_sh").val();
                console.log(email)
                $.ajax({
                    url: "/EmailAuth",
                    method: "get",
                    contentType: "application/x-www-form-urlencoded; charset=UTF-8",
                    data: {"email": email},
                    success: function () {
                        alert("인증번호를 보내드렸습니다. 번호 입력을 해주십시오")
                    },
                    error: function () {
                        alert("인증번호 발송에 실패하였습니다. 다시 시도해십시오.")
                    }
                })
            })
        })
    </script>
    <script>
        document.addEventListener("DOMContentLoaded", function () {
            const CheckCheck = document.getElementById("dblCheck11");
            CheckCheck.addEventListener('click', function () {
                const form = document.getElementById("list_sh")
                let Authcode = form['Authcode'].value
                console.log(Authcode)
                $.ajax({
                    url: "/EmailDone",
                    method: "post",
                    data: {"Authcode": Authcode},
                    success: function(data){
                        console.log()
                        if(data == 1){
                            alert("인증 성공하였습니다. 회원가입을 계속 진행해주십시오");
                        }else{
                            alert("인증에 실패하였습니다. 다시 시도해주세요.")
                        }
                    }
                })
            })
        })
    </script>
</head>
<body>
<div id="flex">
    <div id="logo_sh"><a href="../index.jsp" id="main"></a></div>
    <div id="listWrap_sh">
        <h1 id="title_sh">회원가입</h1>
        <form name="member_insert" id="list_sh" action="../registerFormServlet" method="post">
            <ul id="forms_sh">
                <li><span> 아이디</span> <input type="text" name="memberId" placeholder=" 아이디(이메일)을 입력해주세요" id="id_sh"></li>
                <li><span> 인증번호</span> <input type="text" name="Authcode" placeholder=" 인증번호를 입력하세요" id="Authcode" required></li>

                <li>
                    <div class="check_sh check1"></div>
                </li>
                <span id="dblCheck1" style="cursor:pointer"> 인증하기</span>
                <span id="dblCheck11" style="cursor:pointer"> 인증완료하기</span>
                <li><span> 비밀번호</span> <input type="password" name="password" placeholder=" 비밀번호" id="password_sh"></li>
                <li>
                    <div class="check_sh check2"></div>
                </li>
                <li><span> 비밀번호 확인</span> <input type="password" name="password_cf" id="password_cf"
                                                 placeholder=" 비밀번호 확인">
                </li>
                <li>
                    <div class="check_sh check3"></div>
                </li>
                <li><span> 별명</span> <input type="text" name="nickName" id="nickName" placeholder=" 별명"></li>
                <li>
                    <div class="check_sh check5"></div>
                </li>
                <span id="dblCheck2" style="cursor:pointer">별명 중복확인</span><br>
                <li>
                    <div class="check_sh check6"></div>
                </li>
                <li><span>우편번호</span> <input id="zipcode" type="text" name="zipCode" readonly placeholder=" 우편번호"></li>
                <span id="dblCheck3" style="cursor:pointer"> 우편번호 검색</span> <br>
                <li><span>집 주소1</span> <input id="address01" type="text" name="address1" readonly placeholder=" 주소">
                </li>
                <li><span>집 주소2</span> <input id="address02" type="text" name="address2" placeholder=" 상세주소"></li>
                <input id="dblCheck4" type="submit" value="회원가입">
            </ul>
        </form>
    </div>
    <div class="footerWrap page onePage6">
        <footer class="page8" id="footer">
            <p class="us">about us</p>
            <div id="membersWrap">
                <p class="member0_sh member_sh"><a href="#" target="_blank">개인정보처리방침</a></p>
                <p class="member1_sh member_sh"><a href="hyehyun.html" target="_blank">김혜현</a></p>
                <p class="member2_sh member_sh"><a href="beomsu.html" target="_blank">오범수</a></p>
                <p class="member3_sh member_sh"><a href="haejun.html" target="_blank">오해준</a></p>
                <p class="member4_sh member_sh"><a
                        href="/include/suhyun.jsp"
                        target="_blank">유수현</a></p>
                <p class="member5_sh member_sh"><a href="https://github.com/hyeahyun2/TeamProject"
                                                   target="_blank">GitHub</a></p>
            </div>
        </footer>
        <!-- 유수현 영역2 끝 -->
    </div>
</div>
</body>
</html>