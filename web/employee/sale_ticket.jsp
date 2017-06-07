<%@ page import="java.util.ArrayList" %>
<%@ page import="edu.xupt.cs.se.model.Play" %>
<%@ page import="java.util.HashSet" %>
<%@ page import="java.util.Set" %>
<%@ page import="edu.xupt.cs.se.model.Schedule" %>
<%@ page import="edu.xupt.cs.se.dao.PlayDAO" %>
<%@ page import="edu.xupt.cs.se.dao.StudioDAO" %><%--
  Created by IntelliJ IDEA.
  User: Shinelon
  Date: 2017/6/5
  Time: 11:54
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>

<head>
    <meta charset="utf-8"/>
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title></title>
    <!-- 若您需要使用Kendo UI Professional，请联系版权人获得合法的授权或许可。 -->
    <!-- Bootstrap css -->
    <link href="https://o.qcloud.com/static_api/v3/assets/bootstrap-3.3.4/css/bootstrap.min.css" rel="stylesheet">
    <!-- kendo ui css -->
    <link href="https://o.qcloud.com/static_api/v3/assets/kendoui-2015.2.624/styles/kendo.common.min.css"
          rel="stylesheet">
    <link href="https://o.qcloud.com/static_api/v3/assets/kendoui-2015.2.624/styles/kendo.default.min.css"
          rel="stylesheet">
    <!-- font-awesome -->
    <link href="https://o.qcloud.com/static_api/v3/assets/fontawesome/css/font-awesome.css" rel="stylesheet">
    <!--蓝鲸提供的公用样式库 -->
    <link href="https://o.qcloud.com/static_api/v3/bk/css/bk.css?v=1.0.1" rel="stylesheet">
    <link href="https://o.qcloud.com/static_api/v3/bk/css/bk_pack.css" rel="stylesheet">
    <!-- 如果要使用Bootstrap的js插件，必须先调入jQuery -->
    <script src="https://o.qcloud.com/static_api/v3/assets/js/jquery-1.10.2.min.js"></script>
    <!-- 包括所有bootstrap的js插件或者可以根据需要使用的js插件调用　-->
    <script src="https://o.qcloud.com/static_api/v3/assets/bootstrap-3.3.4/js/bootstrap.min.js"></script>
    <!-- 包括所有kendoui的js插件或者可以根据需要使用的js插件调用　-->
    <script src="https://o.qcloud.com/static_api/v3/assets/kendoui-2015.2.624/js/kendo.all.min.js"></script>
    <script src="https://o.qcloud.com/static_api/v3/bk/js/bk.js"></script>
    <!-- 数据埋点统计 -->
    <script src="https://o.qcloud.com/static_api/analysis.js"></script>
    <!-- 以下两个插件用于在IE8以及以下版本浏览器支持HTML5元素和媒体查询，如果不需要用可以移除 -->
    <!--[if lt IE 9]>
    <script src="https://o.qcloud.com/static_api/v3/assets/js/html5shiv.min.js"></script>
    <script src="https://o.qcloud.com/static_api/v3/assets/js/respond.min.js"></script><![endif]-->
    <link href="https://o.qcloud.com/static_api/v3/assets/artDialog-6.0.4/css/ui-dialog.css" rel="stylesheet">
    <script src="https://o.qcloud.com/static_api/v3/assets/artDialog-6.0.4/dist/dialog-min.js"></script>

</head>

<body class="bg-white" data-bg-color="bg-white">
<div class="king-page-box">
    <div class="king-layout1-header">
        <nav>
            <div class="king-header2 navbar navbar-blue  f14">
                <div class="nav-container">
                    <div class="navbar-header">
                        <button class="pull-right visible-xs navbar-toggle collapsed navbar-toggle-sm" type="button"
                                data-toggle="collapse" data-target="#king-header2-navbar-collapse"><i
                                class="fa fa-fw fa-ellipsis-v"> </i></button>
                        <a class="navbar-brand" href="javascript:;" title="故障预警系统">
                            <img src="https://o.qcloud.com/static_api/v3/bk/images/logo.png" alt="" class="logo"
                                 style="margin-top:-5px;"> </a>
                    </div>
                    <div class="navbar-collapse collapse" id="king-header2-navbar-collapse">
                        <ul class="nav navbar-nav navbar-left hidden-sm"></ul>
                        <ul class="nav navbar-nav navbar-right">
                            <li>
                                <a href="javascript:void(0)" class="dropdown-toggle" data-toggle="dropdown"> <i
                                        class="fa fa-cog"> </i>
                                    <span class="ml10"> 管理配置 </span>
                                </a>
                            </li>
                            <li>
                                <a href="javascript:void(0)">
                                    <span> admin </span>
                                    <span class="avatar">
                                            <img src="https://o.qcloud.com/static_api/v3/components/horizontal_nav9/images/avatar.jpg"> </span>
                                </a>
                            </li>
                            <li><a href="javascript:void(0)"> 退出 </a></li>
                        </ul>
                    </div>
                </div>
            </div>
        </nav>
    </div>
    <div class="king-layout1-main clearfix">
        <!-- sidebar start -->
        <div class="king-layout1-sidebar" style="width:216px;">
            <nav style="height:100%;" class="   f14">
                <div class="king-vertical-nav4">
                    <div class="sidebar-inner">
                        <ul class="navi">
                            <li class="has_submenu current open c-open active">
                                <a href="javascript:void(0);"> <i class="fa fa-gear "> </i>
                                    <span>常用操作</span>
                                    <span class="pull-right"><i class="fa fa-angle-down"></i></span>
                                </a>
                                <ul class="sub-menu">
                                    <li><a href="/employee/sale/">售票<i class="fa fa-chevron-right pull-right"></i></a>
                                    </li>
                                    <li><a href="">退票<i class="fa fa-chevron-right pull-right"></i></a></li>
                                    <li><a href="">影片查询<i class="fa fa-chevron-right pull-right"></i></a></li>
                                    <li><a href="">演出计划查询<i class="fa fa-chevron-right pull-right"></i></a></li>
                                    <li><a href="">统计<i class="fa fa-chevron-right pull-right"></i></a></li>
                                </ul>
                            </li>
                            <li class="has_submenu current open c-open">
                                <a href="javascript:void(0);"> <i class="fa fa-wrench "> </i>
                                    <span>系统管理</span>
                                    <span class="pull-right"><i class="fa fa-angle-down"></i></span>
                                </a>
                                <ul class="sub-menu">
                                    <li><a href="javascript:void(0);">更改密码<i class="fa fa-chevron-right pull-right"></i></a>
                                    </li>
                                </ul>
                            </li>
                        </ul>
                    </div>
                </div>
            </nav>
        </div>
        <!-- sidebar end -->
        <!-- content start -->
        <div class="king-content-wrap">
            <div class="king-layout1-content" style="margin-left: 216px;">
                <!-- <div class="king-page-topbar pl20 pr20 {{ marginStyle }}"> -->
                <div class="king-page-topbar ml15">
                    <h3 class="king-topbar-title mb0">
                        <span></span>
                    </h3>
                    <ul class="breadcrumb king-breadcrumb pl0 bg-transparent">
                        <li><a href="/employee/">首页</a></li>
                        <li>售票</li>
                    </ul>
                </div>
                <!-- 面板 编辑 开始 -->
                <!-- 面板 编辑 结束 -->
                <div class="panel panel-default mb0">
                    <div class="panel-heading">售票</div>
                    <div class="panel-body">
                        <div class="container-fluid m10 bg-white">
                            <div class="row">
                                <form class="form-horizontal" method="post" action="/employee/sale/">
                                    <div class="col-md-3">

                                        <div class="form-group clearfix ">
                                            <label class="col-sm-3 control-label bk-lh30 pt0">影片：</label>
                                            <div class="col-sm-9">
                                                <select name="play" class="form-control bk-valign-top">
                                                    <%
                                                        ArrayList<Play> plays = (ArrayList<Play>) request.getAttribute("plays");
                                                        for (Play play : plays) {
                                                    %>
                                                    <option value="<%=play.getId()%>"><%=play.getName()%>
                                                    </option>
                                                    <%
                                                        }
                                                    %>
                                                </select>
                                            </div>
                                        </div>
                                    </div>
                                    <div class="col-md-3">
                                        <div class="form-group clearfix ">
                                            <label class="col-sm-3 control-label bk-lh30 pt0">日期：</label>
                                            <div class="col-sm-9">
                                                <select name="time" class="form-control bk-valign-top">
                                                    <option value="all">所有</option>
                                                    <%
                                                        Set<String> time = (HashSet<String>) request.getAttribute("time");
                                                        for (String str : time) {
                                                    %>
                                                    <option value="<%=str%>"><%=str%>
                                                    </option>
                                                    <%
                                                        }
                                                    %>
                                                </select>
                                            </div>
                                        </div>
                                    </div>
                                    <div class="col-md-3">
                                        <div class="form-group clearfix">
                                            <div class="col-sm-9 col-sm-offset-3">
                                                <button type="submit" class="king-btn mr10  king-success">查询</button>
                                            </div>
                                        </div>
                                    </div>
                                </form>
                            </div>
                        </div>
                        <table class="table mb0 pr15 ranger-box table-striped table-bordered table-header-bg table-out-bordered table-hover ">
                            <thead>
                            <tr>
                                <th style="width: 10px;">#</th>
                                <th style="width: 10%;">演出计划</th>
                                <th style="width: 30%;">剧目</th>
                                <th style="width: 30%;">演出厅</th>
                                <th style="width: 10%">放映时间</th>
                                <th style="width: 8%;">票价</th>
                                <%--<th>操作</th>--%>
                            </tr>
                            </thead>
                            <tbody>
                            <%
                                if (null != request.getAttribute("schedules")) {
                                    ArrayList<Schedule> schedules = (ArrayList<Schedule>) request.getAttribute("schedules");
                                    PlayDAO playDAO = new PlayDAO();
                                    StudioDAO studioDAO = new StudioDAO();
                                    for (int i = 0; i < schedules.size(); i++) {
                                        String str = schedules.get(i).getTime().substring(0, 4) + "-" + schedules.get(i).getTime().substring(4, 6) + '-' + schedules.get(i).getTime().substring(6, 8) + "&nbsp;" + schedules.get(i).getTime().substring(8, 10) + ":" + schedules.get(i).getTime().substring(10, 12) + ":" + schedules.get(i).getTime().substring(12, 14);
                            %>
                            <tr>
                                <td style="width: 100px;"><%=i + 1%>
                                </td>
                                <td style="width: 10%;"><%=schedules.get(i).getId()%>
                                </td>
                                <td style="width: 15%;"><%=playDAO.getPlayByID(schedules.get(i).getPlay_id()).getName()%>
                                </td>
                                <td style="width: 10%;"><%=studioDAO.getStudioByID(schedules.get(i).getStudio_id()).getName()%>
                                </td>
                                <td style="width: 15%"><%=str%>
                                </td>
                                <td style="width: 10%;"><%=schedules.get(i).getPrice()%>
                                </td>
                            </tr>
                            <%
                                    }
                                }
                            %>
                            </tbody>
                        </table>
                        <form class="form-horizontal">
                            <section>
                                <div class="form-group clearfix">
                                    <div class="col-sm-offset-3 col-sm-9">
                                        <div class="bk-horizontal-line"></div>
                                    </div>
                                </div>
                            </section>
                        </form>
                        <form class="form-horizontal">
                            <div class="container-fluid mb0 ">
                                <div class="row">
                                    <div class="col-md-4">
                                        <div class="container-fluid mb0 ">
                                            <div class="row">
                                                <div class="col-md-12">
                                                    <div class="form-group clearfix ">
                                                        <label class="col-sm-3 control-label bk-lh30 pt0">演出计划：</label>
                                                        <div class="col-sm-9">
                                                            <select name="schedule" id="schedule"
                                                                    class="form-control bk-valign-top">
                                                                <%
                                                                    ArrayList<Schedule> mysch = (ArrayList<Schedule>) request.getAttribute("mysch");
                                                                    for (Schedule schedule : mysch) {
                                                                %>
                                                                <option value="<%=schedule.getId()%>"><%=schedule.getId()%>
                                                                </option>
                                                                <%
                                                                    }
                                                                %>
                                                            </select>
                                                        </div>
                                                    </div>
                                                </div>
                                            </div>
                                        </div>
                                    </div>
                                    <div class="col-md-4">
                                        <div class="container-fluid mb0 ">
                                            <div class="row">
                                                <div class="col-md-6">
                                                    <div class="form-group clearfix ">
                                                        <label class="col-sm-3 control-label bk-lh30 pt0">行：</label>
                                                        <div class="col-sm-9">
                                                            <select id="row" name="row"
                                                                    class="form-control bk-valign-top">
                                                                <%
                                                                    StudioDAO studioDAO = new StudioDAO();
                                                                    for (int i = 1; i <= studioDAO.getStudioByID(mysch.get(0).getStudio_id()).getRow(); i++) {
                                                                %>
                                                                <option value="<%=i%>"><%=i%>
                                                                </option>
                                                                <%
                                                                    }
                                                                %>
                                                            </select>
                                                        </div>
                                                    </div>
                                                </div>
                                                <div class="col-md-6">
                                                    <div class="form-group clearfix ">
                                                        <label class="col-sm-3 control-label bk-lh30 pt0">列：</label>
                                                        <div class="col-sm-9">
                                                            <select id="col" name="col"
                                                                    class="form-control bk-valign-top">
                                                                <%
                                                                    for (int i = 1; i <= studioDAO.getStudioByID(mysch.get(0).getStudio_id()).getCol(); i++) {
                                                                %>
                                                                <option value="<%=i%>"><%=i%>
                                                                </option>
                                                                <%
                                                                    }
                                                                %>
                                                            </select>
                                                        </div>
                                                    </div>
                                                </div>
                                            </div>
                                        </div>
                                    </div>
                                    <div class="col-md-4">
                                        <div class="container-fluid mb0 ">
                                            <div class="row">
                                                <div class="col-md-8">
                                                    <div class="form-group clearfix">
                                                        <div class="col-sm-9 col-sm-offset-3">
                                                            <button type="button" id="submit"
                                                                    class="king-btn mr10  king-success">
                                                                提交
                                                            </button>
                                                        </div>
                                                    </div>
                                                </div>
                                                <div class="col-md-4"></div>
                                            </div>
                                        </div>
                                    </div>
                                </div>
                            </div>
                        </form>
                    </div>
                </div>
            </div>
        </div>
        <!-- content end -->
    </div>
</div>
<script>
    $(function () {
        var li = $('.navi li');
        li.each(function () {
            $(this).click(function () {
                if ($(this).hasClass('c-open')) {
                    $(this).find('ul').slideUp(350);
                    $(this).removeClass('c-open');
                } else {
                    $(this).find('ul').slideDown(350);
                    $(this).addClass('c-open');
                }
            })
        });
        $("#schedule").change(function () {
            $.post("/employee/sale/",
                {
                    type: "getrc",
                    id: this.value
                },
                function (data, status) {
                    if (status == 'success') {
                        var jsobj = JSON.parse(data);
                        var row = jsobj.row;
                        var col = jsobj.col;
                        var rowhtml = "";
                        var colhtml = "";
                        for (var i = 1; i <= row; i++) {
                            rowhtml += "<option value=" + i + ">" + i + "</option>"
                        }
                        for (var i = 1; i <= col; i++) {
                            colhtml += "<option value=" + i + ">" + i + "</option>"
                        }
                        $("#row").html(rowhtml);
                        $("#col").html(colhtml);
                    }
                }
            );
        });

    });
</script>


<button class="king-btn king-info mb15" data-code="2" id="plugin4_demo2">模态对话框</button>
<script type="text/javascript">
    //模态对话框
    $("#submit").click(function () {
        $.post("/employee/sale/",
            {
                schedule: $("#schedule").val(),
                row: $("#row").val(),
                col: $("#col").val()
            },
            function (data, status) {
                if (status == 'success') {
                    var jsobj = JSON.parse(data);
                    var message = jsobj.message;
                    var sta = jsobj.status;
                    if(sta == "success"){
                        var d = dialog({
                            width: 260,
                            title: 'message',
                            content: "订购成功",
                            okValue: '确定',
                            ok: function () {
                                // do something
                            },
                            cancelValue: '取消',
                            cancel: function () {
                                // do something
                            }
                        });
                        d.showModal();
                    }else{
                        var d = dialog({
                            width: 260,
                            title: 'message',
                            content: message,
                            okValue: '确定',
                            ok: function () {
                                // do something
                            },
                            cancelValue: '取消',
                            cancel: function () {
                                // do something
                            }
                        });
                        d.showModal();
                    }

                }
            }
        );


    });
</script>
</body>

</html>
