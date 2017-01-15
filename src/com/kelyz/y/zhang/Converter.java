package com.kelyz.y.zhang;

import java.awt.Component;
import java.util.zip.Adler32;
import java.util.zip.CRC32;
import java.util.zip.Checksum;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;

import org.apache.commons.codec.binary.Hex;
import org.apache.commons.codec.digest.DigestUtils;

public class Converter {
	
	public static String toChecksum(String algo, byte [] data) {
		
		if (algo == "md5") {
			return new String(Hex.encodeHex(DigestUtils.md5(data)));
		}
		
		else if (algo == "sha1") {
			return new String(Hex.encodeHex(DigestUtils.sha1(data)));
		}
		
		else if (algo == "sha256") {
			return new String(Hex.encodeHex(DigestUtils.sha256(data)));
		}
		
		else if (algo == "sha384") {
			return new String(Hex.encodeHex(DigestUtils.sha384(data)));
		}
		
		else if (algo == "sha512") {
			return new String(Hex.encodeHex(DigestUtils.sha512(data)));
		}
		
		else if (algo == "crc32") {
			Checksum cs = new CRC32();
			cs.update(data, 0, data.length);
			long value = cs.getValue();
			return Long.toHexString(value);
		}
		
		else if (algo == "adler32") {
			Checksum cs = new Adler32();
			cs.update(data, 0, data.length);
			long value = cs.getValue();
			return Long.toHexString(value);
		}
		
		else return new String("Not Supported");
	}
	
	public static void toLabel(String algorithm, String input, String conversion, JPanel panel) {
		
		JTextArea algoType = new JTextArea(algorithm + ": " + input, 1, 20);
	    algoType.setWrapStyleWord(true);
	    algoType.setLineWrap(true);
	    algoType.setOpaque(false);
	    algoType.setEditable(false);
		JTextField output = new JTextField(conversion, 35);
		panel.add(algoType);
		panel.add(output);
		output.setAlignmentX(Component.CENTER_ALIGNMENT);
		panel.validate();
	}
}
