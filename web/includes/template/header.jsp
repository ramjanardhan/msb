<%-- 
    Document   : header
    Created on : Feb 3, 2015, 7:52:40 PM
    Author     : Nagireddy
--%>


<%@ page contentType="text/html; charset=UTF-8" errorPage="../exception/403.jsp"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ taglib prefix="sx" uri="/struts-dojo-tags" %>
<%@ page import="java.util.List" isErrorPage="true"%>
<%@ page import="com.mss.msp.util.ApplicationConstants"%>
<script type="text/JavaScript" src="<s:url value="/includes/js/Ajax/AppConstants.js"/>"></script>
<script type="text/JavaScript" src="<s:url value="/includes/js/Ajax/GeneralAjax.js"/>"></script>
<link rel="stylesheet" type="text/css" href="<s:url value="/includes/css/general/general.css"/>">
<link rel="stylesheet" type="text/css" href="<s:url value="/includes/css/main.css"/>">
<link rel="stylesheet" type="text/css" href="<s:url value="/includes/css/responsive_queries.css"/>">
<!DOCTYPE html>

<%-- <div class="row headerAlingment"></div>--%>
<div class="row">
    <div class="col-sm-6">

        <ul class="nav nav-pills header_image"  style="margin-left:9px">
            <s:if test="#session.userId == null">
                <li><a id="headerLogoutLink" href="<%=request.getContextPath()%>/general/logout.action"><img src="<s:url value="/includes/images/logo30.png"/>" alt="loin" width="200" height="44"/></a></li>
                    </s:if>
                    <s:else>
                <li><a id="headerPopupLink" href="#modal-one" ><img src="<s:url value="/includes/images/logo30.png"/>" alt="loin" width="200" height="44"/></a></li>
                <div class="ownmodal" id="modal-one" aria-hidden="true">
                    <div class="ownmodal-dialog">
                        <div class="ownmodal-header">
                            <h2>Session will Expired</h2>
                            <a href="#ownclose" class="ownbtn-close" aria-hidden="true"><i class="fa fa-times"></i></a> 
                        </div>
                        <div class="ownmodal-body">
                            <p>If you click on OK then your session will be closed</p>
                        </div>
                        <div class="ownmodal-footer">
                            <a id="headerLogoutLink" href="<%=request.getContextPath()%>/general/logout.action" class="ownbtn" >OK</a>
                            <a id="headerCancelLink" href="#ownclose" class="ownbtn">Cancel</a> 
                            <!--CHANGED TO "#close"-->
                        </div>
                    </div>
                </div>
            </s:else>
        </ul>

    </div>




    <s:if test="#session.userId == null">  
        <div class="social-icons col-sm-6">
            <ul class="nav navbar-nav navbar-right pull-right register">

                <s:if test="home!='No'&&home!='Logout'">
                    <li class="sign-in sign_in"><a id="headerSigninLink" class="login_open" href="#" data-toggle="modal" data-target="#myLogin">Sign in</a></li>

                    <li><a id="headerRegisterLink" href="<%=request.getContextPath()%>/general/register.action?home=No">Register</a></li>
                </s:if>
                <s:if test="home=='No'||home=='Logout'">
                    <li><a id="headerHomeLink" href="<%=request.getContextPath()%>/general/login.action"><i class="fa fa-home fa-size"></i></a></i></li>
                </s:if>

            </ul>

        </div>	
        			
    </s:if>
    <s:else>
        <div class="col-sm-6" id="col-sm-6" style="padding-bottom: 0px">
            <div class=" pull-right anchorstyle">
                <%
                    String usrId = session.getAttribute(ApplicationConstants.USER_ID).toString();
                    String orgId = session.getAttribute(ApplicationConstants.ORG_ID).toString();

                %>
                <ul class="" style="margin-top: -10px ">
                    <li class="dropdown_profilemenu">
                        <table>
                            <td> 
                                <s:url id="image" action="rImage" namespace="/renderImage">
                                    <s:param name="path" value="%{#session.usrImagePath}"></s:param>
                                </s:url>
                                <img alt="Employee Image" src="<s:property value="#image"/>"  alt="login" height="30px" width="27px">
                            </td>
                            <td style="white-space: nowrap">
                                <ul class="dropdown-menu pull-right multi-column columns-2" style="height:auto;" role="menu" aria-labelledby="dropdownMenu2">
                                    <li style="position: absolute;top:-8px;left: 52%;"><div class="arrow"></div></li>
                                    <div class="row ">
                                        <div class="col-sm-5">
                                            <ul class="multi-column-dropdown">
                                                <li><a id="changePwdLink" href="<%=request.getContextPath()%>/general/changeMyPassword.action">Change&nbsp;Pwd&nbsp;&nbsp;&nbsp;&nbsp;</a>
                                                </li>
                                                <li class="divider"></li>
                                            </ul>
                                        </div>
                                        <div class="col-sm-5">
                                            <ul class="multi-column-dropdown">
                                                <li><a id="viewProfileLink" href="/<%=ApplicationConstants.CONTEXT_PATH%>/acc/accountcontactedit.action?contactId=<%=usrId%>&accountSearchID=<%=orgId%>&flag=customerlogin">View&nbsp;Profile</a>
                                                </li>
                                                <li class="divider"></li>
                                                <li><a id="logoutLink" style="text-align:left" href="<%=request.getContextPath()%>/general/logout.action"><i class="fa fa-power-off">&nbsp;&nbsp;Logout</i></a></li>
                                            </ul>
                                        </div>
                                    </div>
                                </ul>
                                <font style="color:#FAF6F6;font-size:12px;font-weight:400;font-family:Roboto,sans-serif">
                                </font>
                                <a id="dropdownMenu2" class="dropdown-toggle" aria-expanded="flase" data-toggle="dropdown" href="#">
                                    <font style="color:#D6E3F2;font-size:12px;font-weight:600;font-family:Roboto,sans-serif">
                                    <s:property value="#session.firstName"/>&nbsp;<s:property value="#session.lastName"/>
                                    <span class=""><i class="fa fa-angle-down"></i></span>
                                    </font></a>
                                <br>
                                <font style="color:#FAF6F6;font-size:12px;font-weight:400;font-family:Roboto,sans-serif">
                                </font>
                                <font style="color:#D6E3F2;font-size:12px;font-weight:600;font-family:Roboto,sans-serif">
                                [<s:property value="#session.primaryrolevalue" />]
                                </font>
                            </td>
                            <td>
                            </td>
                        </table>
                    </li>
                </ul>
            </div>
        </div>
    </s:else>
</div>

