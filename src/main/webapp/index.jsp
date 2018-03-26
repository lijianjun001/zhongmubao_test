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
            platform: "03",
            version:""
        };
//        var data = {
//            platform: "01",
//            account:"18888888887",
//            password:"123456",
//            smsCode:"423169",
//            referenceCode: "Y96588"
//        };

        console.info(JSON.stringify(data));

        $.ajax({
            type: "POST",
            url: "/customer/message/indexLayer",
            data: JSON.stringify(data),
            contentType: 'application/json;charset=UTF-8',
            dataType: "json",                                     //预期服务器返回类型

            headers: {
                Authorization: "YR3D+mEt8SQSBFNpCoOLiJTh/nZMACMncBFB0C35vJjqFv1gVrk2g3uVvIwSPqSoDnnOWSIzPywvYiswNAOmuqyhymtVphmdBnYRvx0H0ricp6x4a49ak7cdo/WWz72h",
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
