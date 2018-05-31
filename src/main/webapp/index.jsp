<%@ page language="java" contentType="text/html;charset=utf-8" pageEncoding="UTF-8" %>

<html>
<META http-equiv="Content-Type" content="text/html; charset=UTF-8">
<body>
<h2>Hello World!</h2>
</body>
<script src="https://cdn.bootcss.com/jquery/3.2.1/jquery.min.js"></script>
<script type="text/javascript">
    $(function () {
//        var  data = {pageIndex:[1,2]};
//        var data = {
//            id:5051,
//            customerId: 4194,
//            provinceCode: '420000',
//            provinceName: '湖北省',
//            cityCode: '420100',
//            cityName: '武汉市',
//            countyCode:'420103',
//            countyName:'江汉区',
//            address:'潜江市江汉油田二医院院长办公室',
//            name:'毛容易',
//            phone:'17701314001',
//            isDefault:0
//        };
//        private String mac;
//        private String imei;
//        private String ip;
//        private String os;
//        private String deviceid;

        var data = {

        };
        var data = {

        };

        console.info(JSON.stringify(data));

        $.ajax({
            type: "POST",
            url: "http://192.168.31.202/my/readPackage/group",
            data: JSON.stringify(data),
            contentType: 'application/json',
            dataType: "json",                                     //预期服务器返回类型

            headers: {
                Authorization: "YR3D+mEt8SQSBFNpCoOLiErO1bYVfohNcvuAOXpD2SJ9hKh1ZrMfDMuGxx2ilgWIUUTLhi9NryCYEZLAv+tdyOYXl2plHrpyyfNznJl8ADE/Q/btDbiAKkgaqHU6uhhWp4CABVfTUIvWPj8mXXsKQxCHDpP8vAqvaZ6bsv3sguFP6eFKJvMCB2mRXzm32lUijWd3cAJvVhAhaWX/Dt6DHA==",
                platform: "04"
            },
            success: function (data) {
                console.log(JSON.stringify(data));
                alert(JSON.stringify(data))
                //alert(data.msg);
            }, error: function (data) {
                alert("Error:" + data.msg);
            }
        });
    });
</script>
</html>
