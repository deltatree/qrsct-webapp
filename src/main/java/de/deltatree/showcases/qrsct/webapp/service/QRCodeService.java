package de.deltatree.showcases.qrsct.webapp.service;

import java.io.IOException;

import javax.servlet.http.HttpServletResponse;

import net.glxn.qrgen.core.image.ImageType;
import net.glxn.qrgen.javase.QRCode;

import org.springframework.stereotype.Component;

import com.google.zxing.qrcode.decoder.ErrorCorrectionLevel;

import de.deltatree.tools.qrsct.QRSCT;

@Component
public class QRCodeService {

	public void stream(HttpServletResponse response, QRSCT qrsct, int width, int height)
			throws IOException {
		response.setContentType("image/png"); //$NON-NLS-1$
		QRCode.from(qrsct.build()).to(ImageType.PNG).withCharset("UTF-8") //$NON-NLS-1$
				.withErrorCorrection(ErrorCorrectionLevel.M).withSize(width, height)
				.writeTo(response.getOutputStream());
	}

}
