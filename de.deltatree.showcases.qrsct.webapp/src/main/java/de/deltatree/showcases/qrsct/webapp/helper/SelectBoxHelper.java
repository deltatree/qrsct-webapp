package de.deltatree.showcases.qrsct.webapp.helper;

import java.util.EnumSet;
import java.util.TreeSet;

import org.apache.commons.lang.StringEscapeUtils;

public class SelectBoxHelper {

	public static <E extends Enum<E>> String renderOptions(Class<E> clazz) {
		return renderOptions(clazz, null);
	}

	public static <E extends Enum<E>> String renderOptions(Class<E> clazz,
			String defaultValue) {
		StringBuffer sb = new StringBuffer();
		for (E e : new TreeSet<E>(EnumSet.allOf(clazz))) {
			sb.append(option(e.name(), e.toString(),
					(defaultValue != null && defaultValue.equalsIgnoreCase(e
							.name()))));
		}
		return sb.toString();
	}

	private static String option(String option, String text, boolean selected) {
		return "<option value=\"" + escape(option) + "\" " + (selected ? "SELECTED" : "") + ">" + escape(text) + "</option>"; //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$ //$NON-NLS-4$ //$NON-NLS-5$ //$NON-NLS-6$
	}

	private static String escape(String option) {
		return StringEscapeUtils.escapeHtml(option);
	}
}
