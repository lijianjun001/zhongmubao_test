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
        var data = {id: 60};
        console.info(JSON.stringify(data));
//        var saveData ={"USER_ID":leader,"Users":users};

        $.ajax({
            type: "POST",
            url: "/customer/sign",
            data: JSON.stringify(data),
            contentType: 'application/json;charset=UTF-8',
            dataType: "json",                                     //预期服务器返回类型
            headers: {
                Authorization: "YR3D+mEt8SQSBFNpCoOLiJTh/nZMACMncBFB0C35vJiIZTI+RV+lbw43y5F4I1SP5UN8D3G4tP4Npa+qKsIGbmuUZDVEwT4tq1/0hXztbi6dOSBduscO4cGBBI6l8ms8",
                Platform: "04"
            },
            success: function (data) {
                //alert(JSON.stringify(data))
                //alert(data.msg);
            }, error: function (data) {
                //alert(data.msg);
            }
        });
    });
</script>
</html>
