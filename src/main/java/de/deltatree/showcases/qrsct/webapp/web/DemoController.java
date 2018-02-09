package de.deltatree.showcases.qrsct.webapp.web;

import java.io.IOException;
import java.io.InputStream;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;

import org.apache.commons.text.StringEscapeUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.google.common.io.ByteStreams;
import com.google.common.io.Resources;
import com.neovisionaries.i18n.CurrencyCode;

import de.deltatree.showcases.qrsct.webapp.service.QRCodeService;
import de.deltatree.tools.qrsct.QRSCT;
import de.deltatree.tools.qrsct.QRSCTCharacterSetEnum;
import de.deltatree.tools.qrsct.QRSCTPurposeEnum;
import de.deltatree.tools.qrsct.QRSCTServiceTagEnum;
import de.deltatree.tools.qrsct.QRSCTVersionEnum;

@Controller
public class DemoController {

	public static final String OK = "<font color=\"green\">OK :-)</font>"; //$NON-NLS-1$

	@Autowired
	private QRCodeService qrsctService;

	@RequestMapping("/renderQRCode")
	public void renderQRCode(
			@RequestParam(value = "serviceTag", required = false, defaultValue = QRSCTDefaultValues.SERVICE_TAG) String serviceTag,
			@RequestParam(value = "version", required = false, defaultValue = QRSCTDefaultValues.VERSION) String version,
			@RequestParam(value = "characterSet", required = false, defaultValue = QRSCTDefaultValues.CHARACTER_SET) String characterSet,
			@RequestParam(value = "bic", required = false, defaultValue = QRSCTDefaultValues.BIC) String bic,
			@RequestParam(value = "name", required = false, defaultValue = QRSCTDefaultValues.NAME) String name,
			@RequestParam(value = "iban", required = false, defaultValue = QRSCTDefaultValues.IBAN) String iban,
			@RequestParam(value = "amountCurrencyCode", required = false, defaultValue = QRSCTDefaultValues.AMOUNT_CURRENCY_CODE) String amountCurrencyCode,
			@RequestParam(value = "amount", required = false, defaultValue = QRSCTDefaultValues.AMOUNT) String amount,
			@RequestParam(value = "purpose", required = false, defaultValue = QRSCTDefaultValues.PURPOSE) String purpose,
			@RequestParam(value = "reference", required = false, defaultValue = QRSCTDefaultValues.REFERENCE) String reference,
			@RequestParam(value = "hint", required = false, defaultValue = QRSCTDefaultValues.HINT) String hint,
			@RequestParam(value = "width", required = false, defaultValue = "222") String width,
			@RequestParam(value = "height", required = false, defaultValue = "222") String height,
			HttpServletResponse response) throws IOException {

		try {
			QRSCT qrsct = new QRSCT().serviceTag(QRSCTServiceTagEnum.valueOf(serviceTag))
					.version(QRSCTVersionEnum.valueOf(version))
					.characterSet(QRSCTCharacterSetEnum.valueOf(characterSet)).bic(bic).name(name).iban(iban)
					.amount(CurrencyCode.valueOf(amountCurrencyCode), Double.valueOf(amount))
					.purpose(QRSCTPurposeEnum.valueOf(purpose)).reference(reference).hint(hint);
			this.qrsctService.stream(response, qrsct, Integer.valueOf(width).intValue(),
					Integer.valueOf(height).intValue());
		} catch (Exception e) {
			response.setContentType("image/png"); //$NON-NLS-1$
			InputStream openStream = Resources.getResource("forbidden.png").openStream();
			ByteStreams.copy(openStream, response.getOutputStream());
			openStream.close();
		}

	}

	@RequestMapping("/checkQRCode")
	@ResponseBody
	public String checkQRCode(
			@RequestParam(value = "serviceTag", required = false, defaultValue = QRSCTDefaultValues.SERVICE_TAG) String serviceTag,
			@RequestParam(value = "version", required = false, defaultValue = QRSCTDefaultValues.VERSION) String version,
			@RequestParam(value = "characterSet", required = false, defaultValue = QRSCTDefaultValues.CHARACTER_SET) String characterSet,
			@RequestParam(value = "bic", required = false, defaultValue = QRSCTDefaultValues.BIC) String bic,
			@RequestParam(value = "name", required = false, defaultValue = QRSCTDefaultValues.NAME) String name,
			@RequestParam(value = "iban", required = false, defaultValue = QRSCTDefaultValues.IBAN) String iban,
			@RequestParam(value = "amountCurrencyCode", required = false, defaultValue = QRSCTDefaultValues.AMOUNT_CURRENCY_CODE) String amountCurrencyCode,
			@RequestParam(value = "amount", required = false, defaultValue = QRSCTDefaultValues.AMOUNT) String amount,
			@RequestParam(value = "purpose", required = false, defaultValue = QRSCTDefaultValues.PURPOSE) String purpose,
			@RequestParam(value = "reference", required = false, defaultValue = QRSCTDefaultValues.REFERENCE) String reference,
			@RequestParam(value = "hint", required = false, defaultValue = QRSCTDefaultValues.HINT) String hint,
			@RequestParam(value = "width", required = false, defaultValue = "222") String width,
			@RequestParam(value = "height", required = false, defaultValue = "222") String height,
			HttpServletResponse response) throws IOException {

		try {
			QRSCT qrsct = new QRSCT().serviceTag(QRSCTServiceTagEnum.valueOf(serviceTag))
					.version(QRSCTVersionEnum.valueOf(version))
					.characterSet(QRSCTCharacterSetEnum.valueOf(characterSet)).bic(bic).name(name).iban(iban)
					.amount(CurrencyCode.valueOf(amountCurrencyCode), Double.valueOf(amount))
					.purpose(QRSCTPurposeEnum.valueOf(purpose)).reference(reference).hint(hint);
			qrsct.toString();
			Integer.valueOf(width);
			Integer.valueOf(height);
		} catch (Exception e) {
			return "<font color=\"red\">ERROR: " + StringEscapeUtils.escapeHtml4(e.getMessage()) + "</font>"; //$NON-NLS-1$ //$NON-NLS-2$
		}

		return OK;
	}

	@RequestMapping({ "/", "/index.html" })
	public String welcome(Map<String, Object> model) {
		return "example"; //$NON-NLS-1$
	}
}
