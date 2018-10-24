<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>尚硅谷会员注册页面</title>
<%@include file="/include/base.jsp" %>
<style type="text/css">
.login_form {
	height: 420px;
	margin-top: 25px;
}
</style>


<script type="text/javascript">
	$(function() {
		//2、点击图片更换验证码
		$("#codeImg").click(function(){
			//改变src,要让浏览器知道这是新请求,如果是同一请求，浏览器有时会从缓存中加载
			var url = "code.jpg?t="+Math.random();
			$(this).prop("src",url);
		});
		
		$(".itxt[name='username']").blur(function(){
			 var  username = $(".itxt[name='username']").val();
			//用户已经输完用户名了
			//向服务器发个请求看用户是否可用
			//验证用户名
			var regUserName = /^[a-zA-Z0-9]{5,18}$/;
			if(regUserName.test(username)){
				//用户名验证成功发请求
				$.get("UserServlet?method=checkuser&username="+username,function(data){
					//alert(data);
					$(".errorMsg").text(data);
				});
			}
			
		});
		//1、验证用户名，密码，确认密码，email是否符合规定
		$("#sub_btn").click(function(){
			//2、验证成功提交表单。
			  //获取用户输入的所有值
			 var  username = $(".itxt[name='username']").val();
			 var  password = $(".itxt[name='password']").val();
			 var  repwd = $(".itxt[name='repwd']").val();
			 var  email = $(".itxt[name='email']").val();
			 var  code = $(".itxt[name='code']").val();
			 
			 //用户名规则：长度>6  <18  不能写非法字符       合法（字母，数字,_,-）;
			 //密码规则：长度>6  <18  不能写非法字符       合法（字母，数字,_,-）;
			 //确认密码规则：和密码相同;
			 //email：  xxxx@.com
			 //验证码：保证用户已经输入值即可
			 
			 //正则表达式：定义正确规则的一个表达式
			 //    作用：验证字符串是否符合表达式规则
			 //使用：
			 //1、创建一个正则表达式
			// var reg = /a/;
			 //2、使用表达式的test方法验证传入的字符串是否符合规则   返回boolean
			 //alert(reg.test("bc"));
			 
			var regUserName = /^[a-zA-Z0-9]{5,18}$/;
			var regPwd = /^[a-zA-Z0-9]{5,18}$/;
			var regEmail = /^([a-z0-9_\.-]+)@([\da-z\.-]+)\.([a-z\.]{2,6})$/;
			
			if(!regUserName.test(username)){
				//如果用户名验证失败
				$(".errorMsg").text("用户名格式错误");
				return false;
			}
			if(!regPwd.test(password)){
				//如果面验证失败
				$(".errorMsg").text("密码格式错误");
				return false;
				
			}
			if(repwd != password){
				$(".errorMsg").text("两次密码不一致!");
				return false;
			}
			if(!regEmail.test(email)){
				//如果面验证失败
				$(".errorMsg").text("邮箱格式错误!");
				return false;
			}
			if(code==""){
				$(".errorMsg").text("请输入验证码!");
				return false;
			}
			//alert(username+"->"+password+"->"+repwd+"->"+email+"-->"+code);
			//3、验证失败，交代为什么失败
			//alert("你好");
			
			
		});
	});
</script>
</head>
<body>
		<div id="login_header">
			<img class="logo_img" alt="" src="static/img/logo.gif" >
		</div>
		
			<div class="login_banner">
			
				<div id="l_content">
					<span class="login_word">欢迎注册</span>
				</div>
				
				<div id="content">
					<div class="login_form">
						<div class="login_box">
							<div class="tit">
								<h1>注册尚硅谷会员</h1>
								<span class="errorMsg">
									${msg==null?"请输入用户名":msg }
									<%-- <%=request.getAttribute("msg")==null?"请输入用户名": request.getAttribute("msg")%> --%>
								</span>
							</div>
							<div class="form">
								<form action="UserServlet" method="post">
									<input type="hidden" name="method" value="regist">
									<label>用户名称：</label>
									<input class="itxt" type="text" placeholder="请输入用户名" autocomplete="off" tabindex="1" name="username" 
									value="${param.username }"/>
									<br />
									<br />
									<label>用户密码：</label>
									<input class="itxt" type="password" placeholder="请输入密码" autocomplete="off" tabindex="1" name="password" />
									<br />
									<br />
									<label>确认密码：</label>
									<input class="itxt" type="password" placeholder="确认密码" autocomplete="off" tabindex="1" name="repwd" />
									<br />
									<br />
									<label>电子邮件：</label>
									<input class="itxt" type="text" placeholder="请输入邮箱地址" autocomplete="off" tabindex="1" name="email" 
									value="${param.email }"/>
									<br />
									<br />
									<label>验证码：</label>
									<input class="itxt" type="text" style="width: 80px;" name="code"/>
									<img alt="" src="code.jpg" style="float: right; margin-right: 40px;width: 120px;height: 40px;" id="codeImg">									
									<br />
									<br />
									<input type="submit" value="注册" id="sub_btn" />
									
								</form>
							</div>
							
						</div>
					</div>
				</div>
			</div>
		<div id="bottom">
			<span>
				尚硅谷书城.Copyright &copy;2015
			</span>
		</div>
</body>
</html>