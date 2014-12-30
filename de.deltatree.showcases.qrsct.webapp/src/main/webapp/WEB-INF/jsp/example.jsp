<!DOCTYPE html>

<%@page import="de.deltatree.showcases.qrsct.webapp.web.DemoController"%>
<%@page import="org.apache.commons.lang.StringEscapeUtils"%>
<%@page import="de.deltatree.showcases.qrsct.webapp.web.QRSCTDefaultValues"%>
<%@page import="com.neovisionaries.i18n.CurrencyCode"%>
<%@page import="de.deltatree.tools.qrsct.QRSCTPurposeEnum"%>
<%@page import="de.deltatree.tools.qrsct.QRSCTCharacterSetEnum"%>
<%@page import="de.deltatree.tools.qrsct.QRSCTVersionEnum"%>
<%@page import="de.deltatree.tools.qrsct.QRSCTServiceTagEnum"%>
<%@page import="de.deltatree.showcases.qrsct.webapp.helper.SelectBoxHelper"%>
<html lang="en">
<head>
<title>Showcase for: https://github.com/deltatree/qrsct</title>
<meta name="author" content="Alexander Widak" />
<meta http-equiv="content-type" content="text/html; charset=utf-8" />
<style>
iframe {
    border: none;
    outline: none;
    overflow: hidden;
}
</style>

<script src="jquery-1.11.2.min.js"></script>  

<script type="text/javascript">

$(document).ready(function() {                        
    $('#submit').click(function(event) {  
        var username=$('#user').val();
     $.get("checkQRCode?" + createGet(),function(responseText) { 
            $('#textContent').html(responseText);         
        });
    });
});

function changeImage()
{
   image = document.getElementById('qrCode');
   image.src = "renderQRCode?" + createGet();
}

function createGet()
{
	return "serviceTag=" + encodeURIComponent(document.qrForm.serviceTag.options[document.qrForm.serviceTag.selectedIndex].value) + 
		"&version=" + encodeURIComponent(document.qrForm.version.options[document.qrForm.version.selectedIndex].value) +
		"&characterSet=" + encodeURIComponent(document.qrForm.characterSet.options[document.qrForm.characterSet.selectedIndex].value) +
		"&bic=" + encodeURIComponent(document.qrForm.bic.value) +
		"&name=" + encodeURIComponent(document.qrForm.name.value) +
		"&iban=" + encodeURIComponent(document.qrForm.iban.value) +
		"&amountCurrencyCode=" + encodeURIComponent(document.qrForm.amountCurrencyCode.options[document.qrForm.amountCurrencyCode.selectedIndex].value) +
		"&amount=" + encodeURIComponent(document.qrForm.amount.value) +
		"&purpose=" + encodeURIComponent(document.qrForm.purpose.value) +
		"&reference=" + encodeURIComponent(document.qrForm.reference.value) +
		"&hint=" + encodeURIComponent(document.qrForm.hint.value) +
		"&width=" + encodeURIComponent(document.qrForm.width.value) +
		"&height=" + encodeURIComponent(document.qrForm.height.value);	
}

</script>

</head>
<body>
	<div align="center">
	<table style="width:333px;">
	<tr>
	<td align="center" valign="middle">
	
	<h1>
		Showcase for:<br>
		<a href="https://github.com/deltatree/qrsct">https://github.com/deltatree/qrsct</a>
	</h1>

	<hr/>

	<form action="#" name="qrForm">
		<table>
			<tr><td>serviceTag<td><td>:</td><td><select name="serviceTag" size="1" style="width:288px;"><%=SelectBoxHelper.renderOptions(QRSCTServiceTagEnum.class,QRSCTDefaultValues.SERVICE_TAG)%></select></td></tr>
			<tr><td>version<td><td>:</td><td><select name="version" size="1" style="width:288px;"><%=SelectBoxHelper.renderOptions(QRSCTVersionEnum.class,QRSCTDefaultValues.VERSION)%></select></td></tr>
			<tr><td>characterSet<td><td>:</td><td><select name="characterSet" size="1" style="width:288px;"><%=SelectBoxHelper.renderOptions(QRSCTCharacterSetEnum.class, QRSCTDefaultValues.CHARACTER_SET)%></select></td></tr>
			<tr><td>bic<td><td>:</td><td><input type="text" name="bic" value="<%=StringEscapeUtils.escapeHtml(QRSCTDefaultValues.BIC)%>" style="width:288px;"/></td></tr>
			<tr><td>name<td><td>:</td><td><input type="text" name="name" value="<%=StringEscapeUtils.escapeHtml(QRSCTDefaultValues.NAME)%>" style="width:288px;"/></td></tr>
			<tr><td>iban<td><td>:</td><td><input type="text" name="iban" value="<%=StringEscapeUtils.escapeHtml(QRSCTDefaultValues.IBAN)%>" style="width:288px;"/></td></tr>
			<tr><td>amount<td><td>:</td><td><select name="amountCurrencyCode" size="1"><%=SelectBoxHelper.renderOptions(CurrencyCode.class,QRSCTDefaultValues.AMOUNT_CURRENCY_CODE)%></select><input type="text" name="amount" style="width:111px;" value="<%=StringEscapeUtils.escapeHtml(QRSCTDefaultValues.AMOUNT)%>"/></td></tr>
			<tr><td>purpose<td><td>:</td><td><select name="purpose" size="1" style="width:288px;"><%=SelectBoxHelper.renderOptions(QRSCTPurposeEnum.class,QRSCTDefaultValues.PURPOSE)%></select></td></tr>
			<tr><td>reference<td><td>:</td><td><input type="text" name="reference" value="<%=StringEscapeUtils.escapeHtml(QRSCTDefaultValues.REFERENCE)%>" style="width:288px;"/></td></tr>
			<tr><td>hint<td><td>:</td><td><input type="text" name="hint" value="<%=StringEscapeUtils.escapeHtml(QRSCTDefaultValues.HINT)%>" style="width:288px;"/></td></tr>
			<tr><td>width<td><td>:</td><td><input type="text" name="width" value="222" style="width:288px;"/></td></tr>
			<tr><td>height<td><td>:</td><td><input type="text" name="height" value="222" style="width:288px;"/></td></tr>
		</table>
		<input type="button" name="ok" value="ok" onclick="changeImage()" id="submit" /><input type="button" name="reset" value="reset" onclick="window.location.reload()">
	</form>

	<hr/>

	<div id="textContent"><%=DemoController.OK%></div>

	<hr/>

	<img src="renderQRCode" id="qrCode">
	
	</td>
	</tr>
	</table>
	</div>
</body>
</html>