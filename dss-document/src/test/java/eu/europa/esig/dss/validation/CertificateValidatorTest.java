package eu.europa.esig.dss.validation;

import static org.junit.Assert.assertNotNull;

import java.io.File;

import org.junit.Test;

import eu.europa.esig.dss.DSSUtils;
import eu.europa.esig.dss.validation.reports.CertificateReports;

public class CertificateValidatorTest {

	@Test
	public void test() {
		CertificateValidator cv = CertificateValidator.fromCertificate(DSSUtils.loadCertificate(new File("src/test/resources/CZ.cer")));
		cv.setCertificateVerifier(new CommonCertificateVerifier());

		CertificateReports reports = cv.validate();

		assertNotNull(reports);
		assertNotNull(reports.getDiagnosticDataJaxb());
		assertNotNull(reports.getXmlDiagnosticData());
		assertNotNull(reports.getDetailedReportJaxb());
		assertNotNull(reports.getXmlDetailedReport());
		assertNotNull(reports.getSimpleReportJaxb());
		assertNotNull(reports.getXmlSimpleReport());
	}

	@Test(expected = NullPointerException.class)
	public void testCertNull() {
		CertificateValidator.fromCertificate(null);
	}

	@Test(expected = NullPointerException.class)
	public void testPolicyNull() {
		CertificateValidator cv = CertificateValidator.fromCertificate(DSSUtils.loadCertificate(new File("src/test/resources/CZ.cer")));
		cv.setCertificateVerifier(new CommonCertificateVerifier());
		cv.validate(null);
	}

	@Test(expected = NullPointerException.class)
	public void testDateNull() {
		CertificateValidator cv = CertificateValidator.fromCertificate(DSSUtils.loadCertificate(new File("src/test/resources/CZ.cer")));
		cv.setCertificateVerifier(new CommonCertificateVerifier());
		cv.setValidationTime(null);
		cv.validate();
	}

}
