package ch.interlis.iox_j.validator;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import ch.ehi.basics.settings.Settings;
import ch.interlis.ili2c.config.Configuration;
import ch.interlis.ili2c.config.FileEntry;
import ch.interlis.ili2c.config.FileEntryKind;
import ch.interlis.ili2c.metamodel.TransferDescription;
import ch.interlis.iom.IomObject;
import ch.interlis.iom_j.Iom_jObject;
import ch.interlis.iox.IoxLogging;
import ch.interlis.iox_j.ObjectEvent;
import ch.interlis.iox_j.logging.LogEventFactory;

public class Datatypes23Test {

	private TransferDescription td=null;
	
	@Before
	public void setUp() throws Exception {
		// ili-datei lesen
		Configuration ili2cConfig=new Configuration();
		FileEntry fileEntry=new FileEntry("src/test/data/validator/Datatypes23.ili", FileEntryKind.ILIMODELFILE);
		ili2cConfig.addFileEntry(fileEntry);
		td=ch.interlis.ili2c.Ili2c.runCompiler(ili2cConfig);
		assertNotNull(td);
	}
	
	// SUCCESSFUL Tests.
	// These Tests which follow were successful.
	
	@Test
	public void booleanTrue(){
		Iom_jObject objTrue=new Iom_jObject("Datatypes23.Topic.ClassA", "o1");
		objTrue.setattrvalue("aBoolean", "true");
		ValidationConfig modelConfig=new ValidationConfig();
		LogCollector logger=new LogCollector();
		LogEventFactory errFactory=new LogEventFactory();
		Settings settings=new Settings();
		Validator validator=new Validator(td, modelConfig,logger,errFactory,settings);
		validator.validate(new ObjectEvent(objTrue));
		// Asserts
		assertTrue(logger.getErrs().size()==0);
	}
	
	@Test
	public void booleanFalse(){
		Iom_jObject objFalse=new Iom_jObject("Datatypes23.Topic.ClassA", "o1");
		objFalse.setattrvalue("aBoolean", "false");
		ValidationConfig modelConfig=new ValidationConfig();
		LogCollector logger=new LogCollector();
		LogEventFactory errFactory=new LogEventFactory();
		Settings settings=new Settings();
		Validator validator=new Validator(td, modelConfig,logger,errFactory,settings);
		validator.validate(new ObjectEvent(objFalse));
		// Asserts
		assertTrue(logger.getErrs().size()==0);
	}
	
	@Test
	public void uuidExample(){
		Iom_jObject objNormal=new Iom_jObject("Datatypes23.Topic.ClassA", "o1");
		objNormal.setattrvalue("aUuid", "123e4567-e89b-12d3-a456-426655440000");
		ValidationConfig modelConfig=new ValidationConfig();
		LogCollector logger=new LogCollector();
		LogEventFactory errFactory=new LogEventFactory();
		Settings settings=new Settings();
		Validator validator=new Validator(td, modelConfig,logger,errFactory,settings);
		validator.validate(new ObjectEvent(objNormal));
		// Asserts
		assertTrue(logger.getErrs().size()==0);
	}
	
	@Test
	public void uuidMinLength(){
		Iom_jObject objMin=new Iom_jObject("Datatypes23.Topic.ClassA", "o1");
		objMin.setattrvalue("aUuid", "00000000-0000-1000-8080-808080808080");
		ValidationConfig modelConfig=new ValidationConfig();
		LogCollector logger=new LogCollector();
		LogEventFactory errFactory=new LogEventFactory();
		Settings settings=new Settings();
		Validator validator=new Validator(td, modelConfig,logger,errFactory,settings);
		validator.validate(new ObjectEvent(objMin));
		// Asserts
		assertTrue(logger.getErrs().size()==0);
	}
	
	@Test
	public void uuidMaxLength(){
		Iom_jObject objMax=new Iom_jObject("Datatypes23.Topic.ClassA", "o1");
		objMax.setattrvalue("aUuid", "ffffffff-ffff-1fff-bf7f-7f7f7f7f7f7f");
		ValidationConfig modelConfig=new ValidationConfig();
		LogCollector logger=new LogCollector();
		LogEventFactory errFactory=new LogEventFactory();
		Settings settings=new Settings();
		Validator validator=new Validator(td, modelConfig,logger,errFactory,settings);
		validator.validate(new ObjectEvent(objMax));
		// Asserts
		assertTrue(logger.getErrs().size()==0);
	}
	
	@Test
	public void dateMinYear(){
		Iom_jObject objMinYear=new Iom_jObject("Datatypes23.Topic.ClassA", "o1");
		objMinYear.setattrvalue("aDate", "1582-1-30");
		ValidationConfig modelConfig=new ValidationConfig();
		LogCollector logger=new LogCollector();
		LogEventFactory errFactory=new LogEventFactory();
		Settings settings=new Settings();
		Validator validator=new Validator(td, modelConfig,logger,errFactory,settings);
		validator.validate(new ObjectEvent(objMinYear));
		// Asserts
		assertTrue(logger.getErrs().size()==0);
	}
	
	@Test
	public void dateMaxYear(){
		Iom_jObject objMaxYear=new Iom_jObject("Datatypes23.Topic.ClassA", "o1");
		objMaxYear.setattrvalue("aDate", "2999-1-30");
		ValidationConfig modelConfig=new ValidationConfig();
		LogCollector logger=new LogCollector();
		LogEventFactory errFactory=new LogEventFactory();
		Settings settings=new Settings();
		Validator validator=new Validator(td, modelConfig,logger,errFactory,settings);
		validator.validate(new ObjectEvent(objMaxYear));
		// Asserts
		assertTrue(logger.getErrs().size()==0);
	}
	
	@Test
	public void dateMinMonth(){
		Iom_jObject objMinMonth=new Iom_jObject("Datatypes23.Topic.ClassA", "o1");
		objMinMonth.setattrvalue("aDate", "2016-1-30");
		ValidationConfig modelConfig=new ValidationConfig();
		LogCollector logger=new LogCollector();
		LogEventFactory errFactory=new LogEventFactory();
		Settings settings=new Settings();
		Validator validator=new Validator(td, modelConfig,logger,errFactory,settings);
		validator.validate(new ObjectEvent(objMinMonth));
		// Asserts
		assertTrue(logger.getErrs().size()==0);
	}
	
	@Test
	public void dateMaxMonth(){
		Iom_jObject objMaxMonth=new Iom_jObject("Datatypes23.Topic.ClassA", "o1");
		objMaxMonth.setattrvalue("aDate", "2016-12-30");
		ValidationConfig modelConfig=new ValidationConfig();
		LogCollector logger=new LogCollector();
		LogEventFactory errFactory=new LogEventFactory();
		Settings settings=new Settings();
		Validator validator=new Validator(td, modelConfig,logger,errFactory,settings);
		validator.validate(new ObjectEvent(objMaxMonth));
		// Asserts
		assertTrue(logger.getErrs().size()==0);
	}
	
	@Test
	public void dateMinDay(){
		Iom_jObject objMinDay=new Iom_jObject("Datatypes23.Topic.ClassA", "o1");
		objMinDay.setattrvalue("aDate", "2016-1-1");
		ValidationConfig modelConfig=new ValidationConfig();
		LogCollector logger=new LogCollector();
		LogEventFactory errFactory=new LogEventFactory();
		Settings settings=new Settings();
		Validator validator=new Validator(td, modelConfig,logger,errFactory,settings);
		validator.validate(new ObjectEvent(objMinDay));
		// Asserts
		assertTrue(logger.getErrs().size()==0);
	}
	
	@Test
	public void dateMaxDay(){
		Iom_jObject objMaxDay=new Iom_jObject("Datatypes23.Topic.ClassA", "o1");
		objMaxDay.setattrvalue("aDate", "2016-1-31");
		ValidationConfig modelConfig=new ValidationConfig();
		LogCollector logger=new LogCollector();
		LogEventFactory errFactory=new LogEventFactory();
		Settings settings=new Settings();
		Validator validator=new Validator(td, modelConfig,logger,errFactory,settings);
		validator.validate(new ObjectEvent(objMaxDay));
		// Asserts
		assertTrue(logger.getErrs().size()==0);
	}
	
	
	@Test
	public void timeMinHour(){
		Iom_jObject objMinHour=new Iom_jObject("Datatypes23.Topic.ClassA", "o1");
		objMinHour.setattrvalue("aTime", "0:30:30.123");
		ValidationConfig modelConfig=new ValidationConfig();
		LogCollector logger=new LogCollector();
		LogEventFactory errFactory=new LogEventFactory();
		Settings settings=new Settings();
		Validator validator=new Validator(td, modelConfig,logger,errFactory,settings);
		validator.validate(new ObjectEvent(objMinHour));
		// Asserts
		assertTrue(logger.getErrs().size()==0);
	}
	
	@Test
	public void timeMaxHour(){
		Iom_jObject objMaxHour=new Iom_jObject("Datatypes23.Topic.ClassA", "o1");
		objMaxHour.setattrvalue("aTime", "23:30:30.123");
		ValidationConfig modelConfig=new ValidationConfig();
		LogCollector logger=new LogCollector();
		LogEventFactory errFactory=new LogEventFactory();
		Settings settings=new Settings();
		Validator validator=new Validator(td, modelConfig,logger,errFactory,settings);
		validator.validate(new ObjectEvent(objMaxHour));
		// Asserts
		assertTrue(logger.getErrs().size()==0);
	}
	
	@Test
	public void timeMinMinutes(){
		Iom_jObject objMinMinute=new Iom_jObject("Datatypes23.Topic.ClassA", "o1");
		objMinMinute.setattrvalue("aTime", "10:0:30.123");
		ValidationConfig modelConfig=new ValidationConfig();
		LogCollector logger=new LogCollector();
		LogEventFactory errFactory=new LogEventFactory();
		Settings settings=new Settings();
		Validator validator=new Validator(td, modelConfig,logger,errFactory,settings);
		validator.validate(new ObjectEvent(objMinMinute));
		// Asserts
		assertTrue(logger.getErrs().size()==0);
	}
	
	@Test
	public void timeMaxMinutes(){
		Iom_jObject objMaxMinute=new Iom_jObject("Datatypes23.Topic.ClassA", "o1");
		objMaxMinute.setattrvalue("aTime", "10:59:30.123");
		ValidationConfig modelConfig=new ValidationConfig();
		LogCollector logger=new LogCollector();
		LogEventFactory errFactory=new LogEventFactory();
		Settings settings=new Settings();
		Validator validator=new Validator(td, modelConfig,logger,errFactory,settings);
		validator.validate(new ObjectEvent(objMaxMinute));
		// Asserts
		assertTrue(logger.getErrs().size()==0);
	}
	
	@Test
	public void timeMinSeconds(){
		Iom_jObject objMinSecond=new Iom_jObject("Datatypes23.Topic.ClassA", "o1");
		objMinSecond.setattrvalue("aTime", "10:30:0.000");
		ValidationConfig modelConfig=new ValidationConfig();
		LogCollector logger=new LogCollector();
		LogEventFactory errFactory=new LogEventFactory();
		Settings settings=new Settings();
		Validator validator=new Validator(td, modelConfig,logger,errFactory,settings);
		validator.validate(new ObjectEvent(objMinSecond));
		// Asserts
		assertTrue(logger.getErrs().size()==0);
	}
	
	@Test
	public void timeMaxSeconds(){
		Iom_jObject objMaxSecond=new Iom_jObject("Datatypes23.Topic.ClassA", "o1");
		objMaxSecond.setattrvalue("aTime", "10:30:59.999");
		ValidationConfig modelConfig=new ValidationConfig();
		LogCollector logger=new LogCollector();
		LogEventFactory errFactory=new LogEventFactory();
		Settings settings=new Settings();
		Validator validator=new Validator(td, modelConfig,logger,errFactory,settings);
		validator.validate(new ObjectEvent(objMaxSecond));
		// Asserts
		assertTrue(logger.getErrs().size()==0);
	}
	
	@Test
	public void dateTimeMinYear(){
		Iom_jObject objMinYear=new Iom_jObject("Datatypes23.Topic.ClassA", "o1");
		objMinYear.setattrvalue("aDateTime", "1582-5-15T12:30:30.500");
		ValidationConfig modelConfig=new ValidationConfig();
		LogCollector logger=new LogCollector();
		LogEventFactory errFactory=new LogEventFactory();
		Settings settings=new Settings();
		Validator validator=new Validator(td, modelConfig,logger,errFactory,settings);
		validator.validate(new ObjectEvent(objMinYear));
		// Assert
		assertTrue(logger.getErrs().size()==0);
	}
	
	@Test
	public void dateTimeMaxYear(){
		Iom_jObject objMaxYear=new Iom_jObject("Datatypes23.Topic.ClassA", "o1");
		objMaxYear.setattrvalue("aDateTime", "2999-5-15T12:30:30.500");
		ValidationConfig modelConfig=new ValidationConfig();
		LogCollector logger=new LogCollector();
		LogEventFactory errFactory=new LogEventFactory();
		Settings settings=new Settings();
		Validator validator=new Validator(td, modelConfig,logger,errFactory,settings);
		validator.validate(new ObjectEvent(objMaxYear));
		// Assert
		assertTrue(logger.getErrs().size()==0);
	}
	
	@Test
	public void dateTimeMaxMonth(){
		Iom_jObject objMaxMonth=new Iom_jObject("Datatypes23.Topic.ClassA", "o1");
		objMaxMonth.setattrvalue("aDateTime", "2016-12-15T12:30:30.500");
		ValidationConfig modelConfig=new ValidationConfig();
		LogCollector logger=new LogCollector();
		LogEventFactory errFactory=new LogEventFactory();
		Settings settings=new Settings();
		Validator validator=new Validator(td, modelConfig,logger,errFactory,settings);
		validator.validate(new ObjectEvent(objMaxMonth));
		// Assert
		assertTrue(logger.getErrs().size()==0);
	}
	
	@Test
	public void dateTimeMinMonth(){
		Iom_jObject objMinMonth=new Iom_jObject("Datatypes23.Topic.ClassA", "o1");
		objMinMonth.setattrvalue("aDateTime", "2016-1-15T12:30:30.500");
		ValidationConfig modelConfig=new ValidationConfig();
		LogCollector logger=new LogCollector();
		LogEventFactory errFactory=new LogEventFactory();
		Settings settings=new Settings();
		Validator validator=new Validator(td, modelConfig,logger,errFactory,settings);
		validator.validate(new ObjectEvent(objMinMonth));
		// Assert
		assertTrue(logger.getErrs().size()==0);
	}
	
	@Test
	public void dateTimeMaxDay(){
		Iom_jObject objMaxDay=new Iom_jObject("Datatypes23.Topic.ClassA", "o1");
		objMaxDay.setattrvalue("aDateTime", "2016-5-31T12:30:30.500");
		ValidationConfig modelConfig=new ValidationConfig();
		LogCollector logger=new LogCollector();
		LogEventFactory errFactory=new LogEventFactory();
		Settings settings=new Settings();
		Validator validator=new Validator(td, modelConfig,logger,errFactory,settings);
		validator.validate(new ObjectEvent(objMaxDay));
		// Assert
		assertTrue(logger.getErrs().size()==0);
	}
	
	@Test
	public void dateTimeMinDay(){
		Iom_jObject objMinDay=new Iom_jObject("Datatypes23.Topic.ClassA", "o1");
		objMinDay.setattrvalue("aDateTime", "2016-5-1T12:30:30.500");
		ValidationConfig modelConfig=new ValidationConfig();
		LogCollector logger=new LogCollector();
		LogEventFactory errFactory=new LogEventFactory();
		Settings settings=new Settings();
		Validator validator=new Validator(td, modelConfig,logger,errFactory,settings);
		validator.validate(new ObjectEvent(objMinDay));
		// Assert
		assertTrue(logger.getErrs().size()==0);
	}
	
	@Test
	public void dateTimeMaxHour(){
		Iom_jObject objMaxHour=new Iom_jObject("Datatypes23.Topic.ClassA", "o1");
		objMaxHour.setattrvalue("aDateTime", "2016-5-15T23:30:30.500");
		ValidationConfig modelConfig=new ValidationConfig();
		LogCollector logger=new LogCollector();
		LogEventFactory errFactory=new LogEventFactory();
		Settings settings=new Settings();
		Validator validator=new Validator(td, modelConfig,logger,errFactory,settings);
		validator.validate(new ObjectEvent(objMaxHour));
		// Assert
		assertTrue(logger.getErrs().size()==0);
	}
	
	@Test
	public void dateTimeMinHour(){
		Iom_jObject objMinHour=new Iom_jObject("Datatypes23.Topic.ClassA", "o1");
		objMinHour.setattrvalue("aDateTime", "2016-5-15T0:30:30.500");
		ValidationConfig modelConfig=new ValidationConfig();
		LogCollector logger=new LogCollector();
		LogEventFactory errFactory=new LogEventFactory();
		Settings settings=new Settings();
		Validator validator=new Validator(td, modelConfig,logger,errFactory,settings);
		validator.validate(new ObjectEvent(objMinHour));
		// Assert
		assertTrue(logger.getErrs().size()==0);
	}
	
	@Test
	public void dateTimeMaxMinute(){
		Iom_jObject objMaxMinute=new Iom_jObject("Datatypes23.Topic.ClassA", "o1");
		objMaxMinute.setattrvalue("aDateTime", "2016-5-15T12:59:30.500");
		ValidationConfig modelConfig=new ValidationConfig();
		LogCollector logger=new LogCollector();
		LogEventFactory errFactory=new LogEventFactory();
		Settings settings=new Settings();
		Validator validator=new Validator(td, modelConfig,logger,errFactory,settings);
		validator.validate(new ObjectEvent(objMaxMinute));
		// Assert
		assertTrue(logger.getErrs().size()==0);
	}
	
	@Test
	public void dateTimeMinMinute(){
		Iom_jObject objMinMinute=new Iom_jObject("Datatypes23.Topic.ClassA", "o1");
		objMinMinute.setattrvalue("aDateTime", "2016-5-15T12:1:30.500");
		ValidationConfig modelConfig=new ValidationConfig();
		LogCollector logger=new LogCollector();
		LogEventFactory errFactory=new LogEventFactory();
		Settings settings=new Settings();
		Validator validator=new Validator(td, modelConfig,logger,errFactory,settings);
		validator.validate(new ObjectEvent(objMinMinute));
		// Assert
		assertTrue(logger.getErrs().size()==0);
	}
	
	@Test
	public void dateTimeMaxSecond(){
		Iom_jObject objMaxSecond=new Iom_jObject("Datatypes23.Topic.ClassA", "o1");
		objMaxSecond.setattrvalue("aDateTime", "2016-5-15T12:30:59.999");
		ValidationConfig modelConfig=new ValidationConfig();
		LogCollector logger=new LogCollector();
		LogEventFactory errFactory=new LogEventFactory();
		Settings settings=new Settings();
		Validator validator=new Validator(td, modelConfig,logger,errFactory,settings);
		validator.validate(new ObjectEvent(objMaxSecond));
		// Assert
		assertTrue(logger.getErrs().size()==0);
	}
	
	@Test
	public void dateTimeMinSecond(){
		Iom_jObject objMinSecond=new Iom_jObject("Datatypes23.Topic.ClassA", "o1");
		objMinSecond.setattrvalue("aDateTime", "2016-5-15T12:30:0.000");
		ValidationConfig modelConfig=new ValidationConfig();
		LogCollector logger=new LogCollector();
		LogEventFactory errFactory=new LogEventFactory();
		Settings settings=new Settings();
		Validator validator=new Validator(td, modelConfig,logger,errFactory,settings);
		validator.validate(new ObjectEvent(objMinSecond));
		// Assert
		assertTrue(logger.getErrs().size()==0);
	}
	
	@Test
	public void numericIntTypeMin(){
		Iom_jObject objMinLength=new Iom_jObject("Datatypes23.Topic.ClassA", "o1");
		objMinLength.setattrvalue("numericInt", "0");
		ValidationConfig modelConfig=new ValidationConfig();
		LogCollector logger=new LogCollector();
		LogEventFactory errFactory=new LogEventFactory();
		Settings settings=new Settings();
		Validator validator=new Validator(td, modelConfig,logger,errFactory,settings);
		validator.validate(new ObjectEvent(objMinLength));
		// Asserts
		assertTrue(logger.getErrs().size()==0);
	}
	
	@Test
	public void numericIntTypeMax(){
		Iom_jObject objMaxLength=new Iom_jObject("Datatypes23.Topic.ClassA", "o1");
		objMaxLength.setattrvalue("numericInt", "10");
		ValidationConfig modelConfig=new ValidationConfig();
		LogCollector logger=new LogCollector();
		LogEventFactory errFactory=new LogEventFactory();
		Settings settings=new Settings();
		Validator validator=new Validator(td, modelConfig,logger,errFactory,settings);
		validator.validate(new ObjectEvent(objMaxLength));
		// Asserts
		assertTrue(logger.getErrs().size()==0);
	}
	
	@Test
	public void numericDecTypeMin(){
		Iom_jObject objMaxLength=new Iom_jObject("Datatypes23.Topic.ClassA", "o1");
		objMaxLength.setattrvalue("numericDec", "0.0");
		ValidationConfig modelConfig=new ValidationConfig();
		LogCollector logger=new LogCollector();
		LogEventFactory errFactory=new LogEventFactory();
		Settings settings=new Settings();
		Validator validator=new Validator(td, modelConfig,logger,errFactory,settings);
		validator.validate(new ObjectEvent(objMaxLength));
		// Asserts
		assertTrue(logger.getErrs().size()==0);
	}
	
	@Test
	public void numericDecTypeMax(){
		Iom_jObject objMaxLength=new Iom_jObject("Datatypes23.Topic.ClassA", "o1");
		objMaxLength.setattrvalue("numericDec", "10.0");
		ValidationConfig modelConfig=new ValidationConfig();
		LogCollector logger=new LogCollector();
		LogEventFactory errFactory=new LogEventFactory();
		Settings settings=new Settings();
		Validator validator=new Validator(td, modelConfig,logger,errFactory,settings);
		validator.validate(new ObjectEvent(objMaxLength));
		// Asserts
		assertTrue(logger.getErrs().size()==0);
	}
	
	@Test
	public void enumerationTypeMin(){
		Iom_jObject objMaxLength=new Iom_jObject("Datatypes23.Topic.ClassA", "o1");
		objMaxLength.setattrvalue("aufzaehlung", "null");
		ValidationConfig modelConfig=new ValidationConfig();
		LogCollector logger=new LogCollector();
		LogEventFactory errFactory=new LogEventFactory();
		Settings settings=new Settings();
		Validator validator=new Validator(td, modelConfig,logger,errFactory,settings);
		validator.validate(new ObjectEvent(objMaxLength));
		// Asserts
		assertTrue(logger.getErrs().size()==0);
	}
	
	@Test
	public void enumerationTypeEins(){
		Iom_jObject objMaxLength=new Iom_jObject("Datatypes23.Topic.ClassA", "o1");
		objMaxLength.setattrvalue("aufzaehlung", "eins");
		ValidationConfig modelConfig=new ValidationConfig();
		LogCollector logger=new LogCollector();
		LogEventFactory errFactory=new LogEventFactory();
		Settings settings=new Settings();
		Validator validator=new Validator(td, modelConfig,logger,errFactory,settings);
		validator.validate(new ObjectEvent(objMaxLength));
		// Asserts
		assertTrue(logger.getErrs().size()==0);
	}
	
	@Test
	public void enumerationTypeVier(){
		Iom_jObject objMaxLength=new Iom_jObject("Datatypes23.Topic.ClassA", "o1");
		objMaxLength.setattrvalue("aufzaehlung", "mehr.vier");
		ValidationConfig modelConfig=new ValidationConfig();
		LogCollector logger=new LogCollector();
		LogEventFactory errFactory=new LogEventFactory();
		Settings settings=new Settings();
		Validator validator=new Validator(td, modelConfig,logger,errFactory,settings);
		validator.validate(new ObjectEvent(objMaxLength));
		// Asserts
		assertTrue(logger.getErrs().size()==0);
	}
	
	@Test
	public void enumerationTypeDrei(){
		Iom_jObject objMaxLength=new Iom_jObject("Datatypes23.Topic.ClassA", "o1");
		objMaxLength.setattrvalue("aufzaehlung", "mehr.zehn");
		ValidationConfig modelConfig=new ValidationConfig();
		LogCollector logger=new LogCollector();
		LogEventFactory errFactory=new LogEventFactory();
		Settings settings=new Settings();
		Validator validator=new Validator(td, modelConfig,logger,errFactory,settings);
		validator.validate(new ObjectEvent(objMaxLength));
		// Asserts
		assertTrue(logger.getErrs().size()==0);
	}
	
	@Test
	public void textTypeTextLimitedMax(){
		Iom_jObject objMaxLength=new Iom_jObject("Datatypes23.Topic.ClassA", "o1");
		objMaxLength.setattrvalue("textLimited", "aaaaabbbbb");
		ValidationConfig modelConfig=new ValidationConfig();
		LogCollector logger=new LogCollector();
		LogEventFactory errFactory=new LogEventFactory();
		Settings settings=new Settings();
		Validator validator=new Validator(td, modelConfig,logger,errFactory,settings);
		validator.validate(new ObjectEvent(objMaxLength));
		// Asserts
		assertTrue(logger.getErrs().size()==0);
	}
	
	@Test
	public void textTypeTextUnLimitedMax(){
		Iom_jObject objMaxLength=new Iom_jObject("Datatypes23.Topic.ClassA", "o1");
		objMaxLength.setattrvalue("textUnlimited", ch.ehi.basics.tools.StringUtility.STRING(20000, ' '));
		ValidationConfig modelConfig=new ValidationConfig();
		LogCollector logger=new LogCollector();
		LogEventFactory errFactory=new LogEventFactory();
		Settings settings=new Settings();
		Validator validator=new Validator(td, modelConfig,logger,errFactory,settings);
		validator.validate(new ObjectEvent(objMaxLength));
		// Asserts
		assertTrue(logger.getErrs().size()==0);
	}
	
	@Test
	public void textTypeMTextLimitedMax(){
		Iom_jObject objMaxLength=new Iom_jObject("Datatypes23.Topic.ClassA", "o1");
		objMaxLength.setattrvalue("mtextLimited", "aaaaabbbbb");
		ValidationConfig modelConfig=new ValidationConfig();
		LogCollector logger=new LogCollector();
		LogEventFactory errFactory=new LogEventFactory();
		Settings settings=new Settings();
		Validator validator=new Validator(td, modelConfig,logger,errFactory,settings);
		validator.validate(new ObjectEvent(objMaxLength));
		// Asserts
		assertTrue(logger.getErrs().size()==0);
	}
	@Test
	public void textTypeMTextLimitedSpecialCharacter(){
		Iom_jObject objMaxLength=new Iom_jObject("Datatypes23.Topic.ClassA", "o1");
		objMaxLength.setattrvalue("mtextLimited", "\n");
		ValidationConfig modelConfig=new ValidationConfig();
		LogCollector logger=new LogCollector();
		LogEventFactory errFactory=new LogEventFactory();
		Settings settings=new Settings();
		Validator validator=new Validator(td, modelConfig,logger,errFactory,settings);
		validator.validate(new ObjectEvent(objMaxLength));
		// Asserts
		assertTrue(logger.getErrs().size()==0);
	}
	
	@Test
	public void textTypeMTextUnLimitedSpecialCharacter(){
		Iom_jObject objMaxLength=new Iom_jObject("Datatypes23.Topic.ClassA", "o1");
		objMaxLength.setattrvalue("mtextUnlimited", ch.ehi.basics.tools.StringUtility.STRING(20000, '\n'));
		ValidationConfig modelConfig=new ValidationConfig();
		LogCollector logger=new LogCollector();
		LogEventFactory errFactory=new LogEventFactory();
		Settings settings=new Settings();
		Validator validator=new Validator(td, modelConfig,logger,errFactory,settings);
		validator.validate(new ObjectEvent(objMaxLength));
		// Asserts
		assertTrue(logger.getErrs().size()==0);
	}
	
	@Test
	public void coordType1DOk(){
		Iom_jObject objWrongFormat=new Iom_jObject("Datatypes23.Topic.ClassA", "o1");
		IomObject coordValue=objWrongFormat.addattrobj("scoord", "COORD");
		coordValue.setattrvalue("C1", "480000.000");
		ValidationConfig modelConfig=new ValidationConfig();
		LogCollector logger=new LogCollector();
		LogEventFactory errFactory=new LogEventFactory();
		Settings settings=new Settings();
		Validator validator=new Validator(td, modelConfig,logger,errFactory,settings);
		validator.validate(new ObjectEvent(objWrongFormat));
		// Asserts
		assertTrue(logger.getErrs().size()==0);
	}
	
	@Test
	public void coordType2DOk(){
		Iom_jObject objWrongFormat=new Iom_jObject("Datatypes23.Topic.ClassA", "o1");
		IomObject coordValue=objWrongFormat.addattrobj("lcoord", "COORD");
		coordValue.setattrvalue("C1", "480000.000");
		coordValue.setattrvalue("C2", "70000.000");
		ValidationConfig modelConfig=new ValidationConfig();
		LogCollector logger=new LogCollector();
		LogEventFactory errFactory=new LogEventFactory();
		Settings settings=new Settings();
		Validator validator=new Validator(td, modelConfig,logger,errFactory,settings);
		validator.validate(new ObjectEvent(objWrongFormat));
		// Asserts
		assertTrue(logger.getErrs().size()==0);
	}
	
	@Test
	public void coordType3DOk(){
		Iom_jObject objWrongFormat=new Iom_jObject("Datatypes23.Topic.ClassA", "o1");
		IomObject coordValue=objWrongFormat.addattrobj("hcoord", "COORD");
		coordValue.setattrvalue("C1", "480000.000");
		coordValue.setattrvalue("C2", "70000.000");
		coordValue.setattrvalue("C3", "0.000");
		ValidationConfig modelConfig=new ValidationConfig();
		LogCollector logger=new LogCollector();
		LogEventFactory errFactory=new LogEventFactory();
		Settings settings=new Settings();
		Validator validator=new Validator(td, modelConfig,logger,errFactory,settings);
		validator.validate(new ObjectEvent(objWrongFormat));
		// Asserts
		assertTrue(logger.getErrs().size()==0);
	}
	
	// FAILING Tests.
	// The following Tests which are given, they throw 1 Error/Test.
	
	@Test
	public void booleanUppercase(){
		Iom_jObject objUppercase=new Iom_jObject("Datatypes23.Topic.ClassA", "o1");
		objUppercase.setattrvalue("aBoolean", "TRUE");
		ValidationConfig modelConfig=new ValidationConfig();
		LogCollector logger=new LogCollector();
		LogEventFactory errFactory=new LogEventFactory();
		Settings settings=new Settings();
		Validator validator=new Validator(td, modelConfig,logger,errFactory,settings);
		validator.validate(new ObjectEvent(objUppercase));
		// Asserts
		assertTrue(logger.getErrs().size()==1);
		assertEquals("value <TRUE> is not a BOOLEAN", logger.getErrs().get(0).getEventMsg());
	}
	
	@Test
	public void booleanNumber(){
		Iom_jObject objNumber=new Iom_jObject("Datatypes23.Topic.ClassA", "o1");
		objNumber.setattrvalue("aBoolean", "8");
		ValidationConfig modelConfig=new ValidationConfig();
		LogCollector logger=new LogCollector();
		LogEventFactory errFactory=new LogEventFactory();
		Settings settings=new Settings();
		Validator validator=new Validator(td, modelConfig,logger,errFactory,settings);
		validator.validate(new ObjectEvent(objNumber));
		// Asserts
		assertTrue(logger.getErrs().size()==1);
		assertEquals("value <8> is not a BOOLEAN", logger.getErrs().get(0).getEventMsg());
	}
	
	@Test
	public void uuidNotAllowedChar(){
		Iom_jObject objNotAllowedChar=new Iom_jObject("Datatypes23.Topic.ClassA", "o1");
		objNotAllowedChar.setattrvalue("aUuid", "123e4567-e89b-12d3-z456-426655440000");
		ValidationConfig modelConfig=new ValidationConfig();
		LogCollector logger=new LogCollector();
		LogEventFactory errFactory=new LogEventFactory();
		Settings settings=new Settings();
		Validator validator=new Validator(td, modelConfig,logger,errFactory,settings);
		validator.validate(new ObjectEvent(objNotAllowedChar));
		// Asserts
		assertTrue(logger.getErrs().size()==1);
		assertEquals("value <123e4567-e89b-12d3-z456-426655440000> is not a valid UUID", logger.getErrs().get(0).getEventMsg());
	}
	
	@Test
	public void uuidLengthToShort(){
		Iom_jObject objLengthToShort=new Iom_jObject("Datatypes23.Topic.ClassA", "o1");
		objLengthToShort.setattrvalue("aUuid", "123e4567-e89b-12d3-b456-42665544000");
		ValidationConfig modelConfig=new ValidationConfig();
		LogCollector logger=new LogCollector();
		LogEventFactory errFactory=new LogEventFactory();
		Settings settings=new Settings();
		Validator validator=new Validator(td, modelConfig,logger,errFactory,settings);
		validator.validate(new ObjectEvent(objLengthToShort));
		// Asserts
		assertTrue(logger.getErrs().size()==1);
		assertEquals("value <123e4567-e89b-12d3-b456-42665544000> is not a valid UUID", logger.getErrs().get(0).getEventMsg());
	}
	
	@Test
	public void uuidLengthToLong(){
		Iom_jObject objLengthToLong=new Iom_jObject("Datatypes23.Topic.ClassA", "o1");
		// Set Attributes
		objLengthToLong.setattrvalue("aUuid", "123e4567-e89b-12d3-b456-4266554400000");
		ValidationConfig modelConfig=new ValidationConfig();
		LogCollector logger=new LogCollector();
		LogEventFactory errFactory=new LogEventFactory();
		Settings settings=new Settings();
		Validator validator=new Validator(td, modelConfig,logger,errFactory,settings);
		validator.validate(new ObjectEvent(objLengthToLong));
		// Asserts
		assertTrue(logger.getErrs().size()==1);
		assertEquals("value <123e4567-e89b-12d3-b456-4266554400000> is not a valid UUID", logger.getErrs().get(0).getEventMsg());
	}
	
	@Test
	public void dateYearToLow(){
		Iom_jObject objYearToLow=new Iom_jObject("Datatypes23.Topic.ClassA", "o1");
		objYearToLow.setattrvalue("aDate", "1580-2-15");
		ValidationConfig modelConfig=new ValidationConfig();
		LogCollector logger=new LogCollector();
		LogEventFactory errFactory=new LogEventFactory();
		Settings settings=new Settings();
		Validator validator=new Validator(td, modelConfig,logger,errFactory,settings);
		validator.validate(new ObjectEvent(objYearToLow));
		// Asserts
		assertTrue(logger.getErrs().size()==1);
		assertEquals("value <1580-2-15> is not a valid Date", logger.getErrs().get(0).getEventMsg());
	}
	
	@Test
	public void dateYearToHigh(){
		Iom_jObject objYearToHigh=new Iom_jObject("Datatypes23.Topic.ClassA", "o1");
		objYearToHigh.setattrvalue("aDate", "3000-2-15");
		ValidationConfig modelConfig=new ValidationConfig();
		LogCollector logger=new LogCollector();
		LogEventFactory errFactory=new LogEventFactory();
		Settings settings=new Settings();
		Validator validator=new Validator(td, modelConfig,logger,errFactory,settings);
		validator.validate(new ObjectEvent(objYearToHigh));
		// Asserts
		assertTrue(logger.getErrs().size()==1);
		assertEquals("value <3000-2-15> is not a valid Date", logger.getErrs().get(0).getEventMsg());
	}
	
	@Test
	public void dateMonthToLow(){
		Iom_jObject objMonthToLow=new Iom_jObject("Datatypes23.Topic.ClassA", "o1");
		objMonthToLow.setattrvalue("aDate", "2016-0-15");
		ValidationConfig modelConfig=new ValidationConfig();
		LogCollector logger=new LogCollector();
		LogEventFactory errFactory=new LogEventFactory();
		Settings settings=new Settings();
		Validator validator=new Validator(td, modelConfig,logger,errFactory,settings);
		validator.validate(new ObjectEvent(objMonthToLow));
		// Asserts
		assertTrue(logger.getErrs().size()==1);
		assertEquals("value <2016-0-15> is not a valid Date", logger.getErrs().get(0).getEventMsg());
	}
	
	@Test
	public void dateMonthToHigh(){
		Iom_jObject objMonthToHigh=new Iom_jObject("Datatypes23.Topic.ClassA", "o1");
		objMonthToHigh.setattrvalue("aDate", "2016-13-15");
		ValidationConfig modelConfig=new ValidationConfig();
		LogCollector logger=new LogCollector();
		LogEventFactory errFactory=new LogEventFactory();
		Settings settings=new Settings();
		Validator validator=new Validator(td, modelConfig,logger,errFactory,settings);
		validator.validate(new ObjectEvent(objMonthToHigh));
		// Asserts
		assertTrue(logger.getErrs().size()==1);
		assertEquals("value <2016-13-15> is not a valid Date", logger.getErrs().get(0).getEventMsg());
	}
	
	@Test
	public void dateDayToLow(){
		Iom_jObject objDayToLow=new Iom_jObject("Datatypes23.Topic.ClassA", "o1");
		objDayToLow.setattrvalue("aDate", "2016-2-0");
		ValidationConfig modelConfig=new ValidationConfig();
		LogCollector logger=new LogCollector();
		LogEventFactory errFactory=new LogEventFactory();
		Settings settings=new Settings();
		Validator validator=new Validator(td, modelConfig,logger,errFactory,settings);
		validator.validate(new ObjectEvent(objDayToLow));
		// Asserts
		assertTrue(logger.getErrs().size()==1);
		assertEquals("value <2016-2-0> is not a valid Date", logger.getErrs().get(0).getEventMsg());
	}
	
	@Test
	public void dateDayToHigh(){
		Iom_jObject objDayToHigh=new Iom_jObject("Datatypes23.Topic.ClassA", "o1");
		objDayToHigh.setattrvalue("aDate", "2016-2-32");
		ValidationConfig modelConfig=new ValidationConfig();
		LogCollector logger=new LogCollector();
		LogEventFactory errFactory=new LogEventFactory();
		Settings settings=new Settings();
		Validator validator=new Validator(td, modelConfig,logger,errFactory,settings);
		validator.validate(new ObjectEvent(objDayToHigh));
		// Asserts
		assertTrue(logger.getErrs().size()==1);
		assertEquals("value <2016-2-32> is not a valid Date", logger.getErrs().get(0).getEventMsg());
	}
	
	@Test
	public void dateFormatWithDots(){
		Iom_jObject objFormatWithDots=new Iom_jObject("Datatypes23.Topic.ClassA", "o1");
		objFormatWithDots.setattrvalue("aDate", "2016.2.15");
		ValidationConfig modelConfig=new ValidationConfig();
		LogCollector logger=new LogCollector();
		LogEventFactory errFactory=new LogEventFactory();
		Settings settings=new Settings();
		Validator validator=new Validator(td, modelConfig,logger,errFactory,settings);
		validator.validate(new ObjectEvent(objFormatWithDots));
		// Asserts
		assertTrue(logger.getErrs().size()==1);
		assertEquals("value <2016.2.15> is not a valid Date", logger.getErrs().get(0).getEventMsg());
	}

	@Test
	public void dateFormatWithSlash(){
		Iom_jObject objFormatSlash=new Iom_jObject("Datatypes23.Topic.ClassA", "o1");
		objFormatSlash.setattrvalue("aDate", "2016/2/15");
		ValidationConfig modelConfig=new ValidationConfig();
		LogCollector logger=new LogCollector();
		LogEventFactory errFactory=new LogEventFactory();
		Settings settings=new Settings();
		Validator validator=new Validator(td, modelConfig,logger,errFactory,settings);
		validator.validate(new ObjectEvent(objFormatSlash));
		// Asserts
		assertTrue(logger.getErrs().size()==1);
		assertEquals("value <2016/2/15> is not a valid Date", logger.getErrs().get(0).getEventMsg());
	}

	@Test
	public void dateLengthToShort(){
		Iom_jObject objLengthToShort=new Iom_jObject("Datatypes23.Topic.ClassA", "o1");
		objLengthToShort.setattrvalue("aDate", "216-2-2");
		ValidationConfig modelConfig=new ValidationConfig();
		LogCollector logger=new LogCollector();
		LogEventFactory errFactory=new LogEventFactory();
		Settings settings=new Settings();
		Validator validator=new Validator(td, modelConfig,logger,errFactory,settings);
		validator.validate(new ObjectEvent(objLengthToShort));
		// Asserts
		assertTrue(logger.getErrs().size()==1);
		assertEquals("value <216-2-2> is not a valid Date", logger.getErrs().get(0).getEventMsg());
	}

	@Test
	public void dateLengthToLong(){
		Iom_jObject objLengthToLong=new Iom_jObject("Datatypes23.Topic.ClassA", "o1");
		// Set Attributes
		objLengthToLong.setattrvalue("aDate", "20016-12-15");
		ValidationConfig modelConfig=new ValidationConfig();
		LogCollector logger=new LogCollector();
		LogEventFactory errFactory=new LogEventFactory();
		Settings settings=new Settings();
		Validator validator=new Validator(td, modelConfig,logger,errFactory,settings);
		validator.validate(new ObjectEvent(objLengthToLong));
		// Asserts
		assertTrue(logger.getErrs().size()==1);
		assertEquals("value <20016-12-15> is not a valid Date", logger.getErrs().get(0).getEventMsg());
	}
	
	@Test
	public void timeHourToHigh(){
		Iom_jObject objHourToHigh=new Iom_jObject("Datatypes23.Topic.ClassA", "o1");
		objHourToHigh.setattrvalue("aTime", "24:59:59.999");
		ValidationConfig modelConfig=new ValidationConfig();
		LogCollector logger=new LogCollector();
		LogEventFactory errFactory=new LogEventFactory();
		Settings settings=new Settings();
		Validator validator=new Validator(td, modelConfig,logger,errFactory,settings);
		validator.validate(new ObjectEvent(objHourToHigh));
		// Asserts
		assertTrue(logger.getErrs().size()==1);
		assertEquals("value <24:59:59.999> is not a valid Time", logger.getErrs().get(0).getEventMsg());
	}
	
	@Test
	public void timeMinuteToHigh(){
		Iom_jObject objMinuteToHigh=new Iom_jObject("Datatypes23.Topic.ClassA", "o1");
		objMinuteToHigh.setattrvalue("aTime", "23:60:59.999");
		ValidationConfig modelConfig=new ValidationConfig();
		LogCollector logger=new LogCollector();
		LogEventFactory errFactory=new LogEventFactory();
		Settings settings=new Settings();
		Validator validator=new Validator(td, modelConfig,logger,errFactory,settings);
		validator.validate(new ObjectEvent(objMinuteToHigh));
		// Asserts
		assertTrue(logger.getErrs().size()==1);
		assertEquals("value <23:60:59.999> is not a valid Time", logger.getErrs().get(0).getEventMsg());
	}

	@Test
	public void timeSecondToHigh(){
		Iom_jObject objSecondToHigh=new Iom_jObject("Datatypes23.Topic.ClassA", "o1");
		objSecondToHigh.setattrvalue("aTime", "23:59:60.000");
		ValidationConfig modelConfig=new ValidationConfig();
		LogCollector logger=new LogCollector();
		LogEventFactory errFactory=new LogEventFactory();
		Settings settings=new Settings();
		Validator validator=new Validator(td, modelConfig,logger,errFactory,settings);
		validator.validate(new ObjectEvent(objSecondToHigh));
		// Asserts
		assertTrue(logger.getErrs().size()==1);
		assertEquals("value <23:59:60.000> is not a valid Time", logger.getErrs().get(0).getEventMsg());
	}

	@Test
	public void timeLengthToShort(){
		Iom_jObject objTimeToShort=new Iom_jObject("Datatypes23.Topic.ClassA", "o1");
		objTimeToShort.setattrvalue("aTime", "5:5:5.55");
		ValidationConfig modelConfig=new ValidationConfig();
		LogCollector logger=new LogCollector();
		LogEventFactory errFactory=new LogEventFactory();
		Settings settings=new Settings();
		Validator validator=new Validator(td, modelConfig,logger,errFactory,settings);
		validator.validate(new ObjectEvent(objTimeToShort));
		// Asserts
		assertTrue(logger.getErrs().size()==1);
		assertEquals("value <5:5:5.55> is not a valid Time", logger.getErrs().get(0).getEventMsg());
	}

	@Test
	public void timeLengthToLong(){
		Iom_jObject objTimeToLong=new Iom_jObject("Datatypes23.Topic.ClassA", "o1");
		objTimeToLong.setattrvalue("aTime", "23:59:59.9990");
		ValidationConfig modelConfig=new ValidationConfig();
		LogCollector logger=new LogCollector();
		LogEventFactory errFactory=new LogEventFactory();
		Settings settings=new Settings();
		Validator validator=new Validator(td, modelConfig,logger,errFactory,settings);
		validator.validate(new ObjectEvent(objTimeToLong));
		// Asserts
		assertTrue(logger.getErrs().size()==1);
		assertEquals("value <23:59:59.9990> is not a valid Time", logger.getErrs().get(0).getEventMsg());
	}
	
	@Test
	public void dateTimeYearToLow(){
		Iom_jObject objYearToLow=new Iom_jObject("Datatypes23.Topic.ClassA", "o1");
		objYearToLow.setattrvalue("aDateTime", "1581-2-29T12:59:59.999");
		ValidationConfig modelConfig=new ValidationConfig();
		LogCollector logger=new LogCollector();
		LogEventFactory errFactory=new LogEventFactory();
		Settings settings=new Settings();
		Validator validator=new Validator(td, modelConfig,logger,errFactory,settings);
		validator.validate(new ObjectEvent(objYearToLow));
		// Asserts
		assertTrue(logger.getErrs().size()==1);
		assertEquals("value <1581-2-29T12:59:59.999> is not a valid DateTime", logger.getErrs().get(0).getEventMsg());
	}
	
	@Test
	public void dateTimeYearToHigh(){
		Iom_jObject objYearToHigh=new Iom_jObject("Datatypes23.Topic.ClassA", "o1");
		objYearToHigh.setattrvalue("aDateTime", "3000-2-29T12:59:59.999");
		ValidationConfig modelConfig=new ValidationConfig();
		LogCollector logger=new LogCollector();
		LogEventFactory errFactory=new LogEventFactory();
		Settings settings=new Settings();
		Validator validator=new Validator(td, modelConfig,logger,errFactory,settings);
		validator.validate(new ObjectEvent(objYearToHigh));
		// Asserts
		assertTrue(logger.getErrs().size()==1);
		assertEquals("value <3000-2-29T12:59:59.999> is not a valid DateTime", logger.getErrs().get(0).getEventMsg());
	}
	
	@Test
	public void dateTimeMonthToLow(){
		Iom_jObject objMonthToLow=new Iom_jObject("Datatypes23.Topic.ClassA", "o1");
		objMonthToLow.setattrvalue("aDateTime", "2016-0-29T12:59:59.999");
		ValidationConfig modelConfig=new ValidationConfig();
		LogCollector logger=new LogCollector();
		LogEventFactory errFactory=new LogEventFactory();
		Settings settings=new Settings();
		Validator validator=new Validator(td, modelConfig,logger,errFactory,settings);
		validator.validate(new ObjectEvent(objMonthToLow));
		// Asserts
		assertTrue(logger.getErrs().size()==1);
		assertEquals("value <2016-0-29T12:59:59.999> is not a valid DateTime", logger.getErrs().get(0).getEventMsg());
	}
	
	@Test
	public void dateTimeMonthToHigh(){
		Iom_jObject objMonthToHigh=new Iom_jObject("Datatypes23.Topic.ClassA", "o1");
		objMonthToHigh.setattrvalue("aDateTime", "2016-13-29T12:59:59.999");
		ValidationConfig modelConfig=new ValidationConfig();
		LogCollector logger=new LogCollector();
		LogEventFactory errFactory=new LogEventFactory();
		Settings settings=new Settings();
		Validator validator=new Validator(td, modelConfig,logger,errFactory,settings);
		validator.validate(new ObjectEvent(objMonthToHigh));
		// Asserts
		assertTrue(logger.getErrs().size()==1);
		assertEquals("value <2016-13-29T12:59:59.999> is not a valid DateTime", logger.getErrs().get(0).getEventMsg());
	}
	
	@Test
	public void dateTimeDayToLow(){
		Iom_jObject objDayToLow=new Iom_jObject("Datatypes23.Topic.ClassA", "o1");
		objDayToLow.setattrvalue("aDateTime", "2016-2-0T12:59:59.999");
		ValidationConfig modelConfig=new ValidationConfig();
		LogCollector logger=new LogCollector();
		LogEventFactory errFactory=new LogEventFactory();
		Settings settings=new Settings();
		Validator validator=new Validator(td, modelConfig,logger,errFactory,settings);
		validator.validate(new ObjectEvent(objDayToLow));
		// Asserts
		assertTrue(logger.getErrs().size()==1);
		assertEquals("value <2016-2-0T12:59:59.999> is not a valid DateTime", logger.getErrs().get(0).getEventMsg());
	}
	
	@Test
	public void dateTimeDayToHigh(){
		Iom_jObject objDayToHigh=new Iom_jObject("Datatypes23.Topic.ClassA", "o1");
		objDayToHigh.setattrvalue("aDateTime", "2016-2-32T12:59:59.999");
		ValidationConfig modelConfig=new ValidationConfig();
		LogCollector logger=new LogCollector();
		LogEventFactory errFactory=new LogEventFactory();
		Settings settings=new Settings();
		Validator validator=new Validator(td, modelConfig,logger,errFactory,settings);
		validator.validate(new ObjectEvent(objDayToHigh));
		// Asserts
		assertTrue(logger.getErrs().size()==1);
		assertEquals("value <2016-2-32T12:59:59.999> is not a valid DateTime", logger.getErrs().get(0).getEventMsg());
	}
	
	@Test
	public void dateTimeHourToHigh(){
		Iom_jObject objHourToHigh=new Iom_jObject("Datatypes23.Topic.ClassA", "o1");
		objHourToHigh.setattrvalue("aDateTime", "2016-2-29T24:59:59.999");
		ValidationConfig modelConfig=new ValidationConfig();
		LogCollector logger=new LogCollector();
		LogEventFactory errFactory=new LogEventFactory();
		Settings settings=new Settings();
		Validator validator=new Validator(td, modelConfig,logger,errFactory,settings);
		validator.validate(new ObjectEvent(objHourToHigh));
		// Asserts
		assertTrue(logger.getErrs().size()==1);
		assertEquals("value <2016-2-29T24:59:59.999> is not a valid DateTime", logger.getErrs().get(0).getEventMsg());
	}
	
	@Test
	public void dateTimeMinuteToHigh(){
		Iom_jObject objMinuteToHigh=new Iom_jObject("Datatypes23.Topic.ClassA", "o1");
		objMinuteToHigh.setattrvalue("aDateTime", "2016-2-29T12:60:59.999");
		ValidationConfig modelConfig=new ValidationConfig();
		LogCollector logger=new LogCollector();
		LogEventFactory errFactory=new LogEventFactory();
		Settings settings=new Settings();
		Validator validator=new Validator(td, modelConfig,logger,errFactory,settings);
		validator.validate(new ObjectEvent(objMinuteToHigh));
		// Asserts
		assertTrue(logger.getErrs().size()==1);
		assertEquals("value <2016-2-29T12:60:59.999> is not a valid DateTime", logger.getErrs().get(0).getEventMsg());
	}
	
	@Test
	public void dateTimeSecondToHigh(){
		Iom_jObject objSecondToHigh=new Iom_jObject("Datatypes23.Topic.ClassA", "o1");
		objSecondToHigh.setattrvalue("aDateTime", "2016-2-29T12:59:60.000");
		ValidationConfig modelConfig=new ValidationConfig();
		LogCollector logger=new LogCollector();
		LogEventFactory errFactory=new LogEventFactory();
		Settings settings=new Settings();
		Validator validator=new Validator(td, modelConfig,logger,errFactory,settings);
		validator.validate(new ObjectEvent(objSecondToHigh));
		// Asserts
		assertTrue(logger.getErrs().size()==1);
		assertEquals("value <2016-2-29T12:59:60.000> is not a valid DateTime", logger.getErrs().get(0).getEventMsg());
	}

	@Test
	public void dateTimeLengthToShort(){
		Iom_jObject objLengthToShort=new Iom_jObject("Datatypes23.Topic.ClassA", "o1");
		objLengthToShort.setattrvalue("aDateTime", "2016-2-2T2:2:2.99");
		ValidationConfig modelConfig=new ValidationConfig();
		LogCollector logger=new LogCollector();
		LogEventFactory errFactory=new LogEventFactory();
		Settings settings=new Settings();
		Validator validator=new Validator(td, modelConfig,logger,errFactory,settings);
		validator.validate(new ObjectEvent(objLengthToShort));
		// Asserts
		assertTrue(logger.getErrs().size()==1);
		assertEquals("value <2016-2-2T2:2:2.99> is not a valid DateTime", logger.getErrs().get(0).getEventMsg());
	}

	@Test
	public void dateTimeLengthToHigh(){
		Iom_jObject objLengthToHigh=new Iom_jObject("Datatypes23.Topic.ClassA", "o1");
		objLengthToHigh.setattrvalue("aDateTime", "2016-12-29T12:59:59.9999");
		ValidationConfig modelConfig=new ValidationConfig();
		LogCollector logger=new LogCollector();
		LogEventFactory errFactory=new LogEventFactory();
		Settings settings=new Settings();
		Validator validator=new Validator(td, modelConfig,logger,errFactory,settings);
		validator.validate(new ObjectEvent(objLengthToHigh));
		// Asserts
		assertTrue(logger.getErrs().size()==1);
		assertEquals("value <2016-12-29T12:59:59.9999> is not a valid DateTime", logger.getErrs().get(0).getEventMsg());
	}

	@Test
	public void dateTimeFormatWithDots(){
		Iom_jObject objFormatWithDots=new Iom_jObject("Datatypes23.Topic.ClassA", "o1");
		objFormatWithDots.setattrvalue("aDateTime", "2016.2.29T12:59:59.999");
		ValidationConfig modelConfig=new ValidationConfig();
		LogCollector logger=new LogCollector();
		LogEventFactory errFactory=new LogEventFactory();
		Settings settings=new Settings();
		Validator validator=new Validator(td, modelConfig,logger,errFactory,settings);
		validator.validate(new ObjectEvent(objFormatWithDots));
		// Asserts
		assertTrue(logger.getErrs().size()==1);
		assertEquals("value <2016.2.29T12:59:59.999> is not a valid DateTime", logger.getErrs().get(0).getEventMsg());
	}
	
	@Test
	public void dateTimeFormatWithoutT() {
		Iom_jObject objFormatWithoutT=new Iom_jObject("Datatypes23.Topic.ClassA", "o1");
		// Set Attributes
		objFormatWithoutT.setattrvalue("aDateTime", "2016-2-29V12:59:59.999");
		ValidationConfig modelConfig=new ValidationConfig();
		LogCollector logger=new LogCollector();
		LogEventFactory errFactory=new LogEventFactory();
		Settings settings=new Settings();
		Validator validator=new Validator(td, modelConfig,logger,errFactory,settings);
		validator.validate(new ObjectEvent(objFormatWithoutT));
		// Asserts
		assertTrue(logger.getErrs().size()==1);
		assertEquals("value <2016-2-29V12:59:59.999> is not a valid DateTime", logger.getErrs().get(0).getEventMsg());
	}
	
	@Test
	public void numericTypeWrongFormat(){
		Iom_jObject objWrongFormat=new Iom_jObject("Datatypes23.Topic.ClassA", "o1");
		objWrongFormat.setattrvalue("numericInt", "a");
		ValidationConfig modelConfig=new ValidationConfig();
		LogCollector logger=new LogCollector();
		LogEventFactory errFactory=new LogEventFactory();
		Settings settings=new Settings();
		Validator validator=new Validator(td, modelConfig,logger,errFactory,settings);
		validator.validate(new ObjectEvent(objWrongFormat));
		// Asserts
		assertTrue(logger.getErrs().size()==1);
		assertEquals("value <a> is not a number", logger.getErrs().get(0).getEventMsg());
	}
	
	@Test
	public void numericTypeMinWrong(){
		Iom_jObject objWrongFormat=new Iom_jObject("Datatypes23.Topic.ClassA", "o1");
		objWrongFormat.setattrvalue("numericInt", "-1");
		ValidationConfig modelConfig=new ValidationConfig();
		LogCollector logger=new LogCollector();
		LogEventFactory errFactory=new LogEventFactory();
		Settings settings=new Settings();
		Validator validator=new Validator(td, modelConfig,logger,errFactory,settings);
		validator.validate(new ObjectEvent(objWrongFormat));
		// Asserts
		assertTrue(logger.getErrs().size()==1);
		assertEquals("value -1 is out of range", logger.getErrs().get(0).getEventMsg());
	}
	
	@Test
	public void numericTypeMaxWrong(){
		Iom_jObject objWrongFormat=new Iom_jObject("Datatypes23.Topic.ClassA", "o1");
		objWrongFormat.setattrvalue("numericInt", "11");
		ValidationConfig modelConfig=new ValidationConfig();
		LogCollector logger=new LogCollector();
		LogEventFactory errFactory=new LogEventFactory();
		Settings settings=new Settings();
		Validator validator=new Validator(td, modelConfig,logger,errFactory,settings);
		validator.validate(new ObjectEvent(objWrongFormat));
		// Asserts
		assertTrue(logger.getErrs().size()==1);
		assertEquals("value 11 is out of range", logger.getErrs().get(0).getEventMsg());
	}
	
	@Test
	public void numericTypeDecWrongFormat(){
		Iom_jObject objWrongFormat=new Iom_jObject("Datatypes23.Topic.ClassA", "o1");
		objWrongFormat.setattrvalue("numericDec", "a");
		ValidationConfig modelConfig=new ValidationConfig();
		LogCollector logger=new LogCollector();
		LogEventFactory errFactory=new LogEventFactory();
		Settings settings=new Settings();
		Validator validator=new Validator(td, modelConfig,logger,errFactory,settings);
		validator.validate(new ObjectEvent(objWrongFormat));
		// Asserts
		assertTrue(logger.getErrs().size()==1);
		assertEquals("value <a> is not a number", logger.getErrs().get(0).getEventMsg());
	}
	
	@Test
	public void numericTypeDecMinWrong(){
		Iom_jObject objWrongFormat=new Iom_jObject("Datatypes23.Topic.ClassA", "o1");
		objWrongFormat.setattrvalue("numericDec", "-1");
		ValidationConfig modelConfig=new ValidationConfig();
		LogCollector logger=new LogCollector();
		LogEventFactory errFactory=new LogEventFactory();
		Settings settings=new Settings();
		Validator validator=new Validator(td, modelConfig,logger,errFactory,settings);
		validator.validate(new ObjectEvent(objWrongFormat));
		// Asserts
		assertTrue(logger.getErrs().size()==1);
		assertEquals("value -1 is out of range", logger.getErrs().get(0).getEventMsg());
	}
	
	@Test
	public void numericTypeDecMaxWrong(){
		Iom_jObject objWrongFormat=new Iom_jObject("Datatypes23.Topic.ClassA", "o1");
		objWrongFormat.setattrvalue("numericDec", "11");
		ValidationConfig modelConfig=new ValidationConfig();
		LogCollector logger=new LogCollector();
		LogEventFactory errFactory=new LogEventFactory();
		Settings settings=new Settings();
		Validator validator=new Validator(td, modelConfig,logger,errFactory,settings);
		validator.validate(new ObjectEvent(objWrongFormat));
		// Asserts
		assertTrue(logger.getErrs().size()==1);
		assertEquals("value 11 is out of range", logger.getErrs().get(0).getEventMsg());
	}
	
	@Test
	public void enumerationTypeWrongSubValue(){
		Iom_jObject objWrongFormat=new Iom_jObject("Datatypes23.Topic.ClassA", "o1");
		objWrongFormat.setattrvalue("aufzaehlung", "mehr.elf");
		ValidationConfig modelConfig=new ValidationConfig();
		LogCollector logger=new LogCollector();
		LogEventFactory errFactory=new LogEventFactory();
		Settings settings=new Settings();
		Validator validator=new Validator(td, modelConfig,logger,errFactory,settings);
		validator.validate(new ObjectEvent(objWrongFormat));
		// Asserts
		assertTrue(logger.getErrs().size()==1);
		assertEquals("value mehr.elf is not a member of the enumeration", logger.getErrs().get(0).getEventMsg());
	}
	
	@Test
	public void enumerationTypeWrongValue(){
		Iom_jObject objWrongFormat=new Iom_jObject("Datatypes23.Topic.ClassA", "o1");
		objWrongFormat.setattrvalue("aufzaehlung", "5");
		ValidationConfig modelConfig=new ValidationConfig();
		LogCollector logger=new LogCollector();
		LogEventFactory errFactory=new LogEventFactory();
		Settings settings=new Settings();
		Validator validator=new Validator(td, modelConfig,logger,errFactory,settings);
		validator.validate(new ObjectEvent(objWrongFormat));
		// Asserts
		assertTrue(logger.getErrs().size()==1);
		assertEquals("value 5 is not a member of the enumeration", logger.getErrs().get(0).getEventMsg());
	}
	
	@Test
	public void textTypeTextLimitedToLong(){
		Iom_jObject objMaxLength=new Iom_jObject("Datatypes23.Topic.ClassA", "o1");
		objMaxLength.setattrvalue("textLimited", "aaaaabbbbbc");
		ValidationConfig modelConfig=new ValidationConfig();
		LogCollector logger=new LogCollector();
		LogEventFactory errFactory=new LogEventFactory();
		Settings settings=new Settings();
		Validator validator=new Validator(td, modelConfig,logger,errFactory,settings);
		validator.validate(new ObjectEvent(objMaxLength));
		// Asserts
		assertTrue(logger.getErrs().size()==1);
		assertEquals("Attribute textLimited is length restricted to 10", logger.getErrs().get(0).getEventMsg());
	}
	
	@Test
	public void textTypeTextLimitedWrongFormat(){
		Iom_jObject objMaxLength=new Iom_jObject("Datatypes23.Topic.ClassA", "o1");
		objMaxLength.setattrvalue("textLimited", "\n");
		ValidationConfig modelConfig=new ValidationConfig();
		LogCollector logger=new LogCollector();
		LogEventFactory errFactory=new LogEventFactory();
		Settings settings=new Settings();
		Validator validator=new Validator(td, modelConfig,logger,errFactory,settings);
		validator.validate(new ObjectEvent(objMaxLength));
		// Asserts
		assertTrue(logger.getErrs().size()==1);
		assertEquals("Attribute textLimited must not contain control characters", logger.getErrs().get(0).getEventMsg());
	}
	
	@Test
	public void textTypeTextUnLimitedWrongFormat(){
		Iom_jObject objMaxLength=new Iom_jObject("Datatypes23.Topic.ClassA", "o1");
		objMaxLength.setattrvalue("textUnlimited", "\n");
		ValidationConfig modelConfig=new ValidationConfig();
		LogCollector logger=new LogCollector();
		LogEventFactory errFactory=new LogEventFactory();
		Settings settings=new Settings();
		Validator validator=new Validator(td, modelConfig,logger,errFactory,settings);
		validator.validate(new ObjectEvent(objMaxLength));
		// Asserts
		assertTrue(logger.getErrs().size()==1);
		assertEquals("Attribute textUnlimited must not contain control characters", logger.getErrs().get(0).getEventMsg());
	}
	
	@Test
	public void textTypeMTextLimitedToHigh(){
		Iom_jObject objMaxLength=new Iom_jObject("Datatypes23.Topic.ClassA", "o1");
		objMaxLength.setattrvalue("mtextLimited", "aaaaabbbbbc");
		ValidationConfig modelConfig=new ValidationConfig();
		LogCollector logger=new LogCollector();
		LogEventFactory errFactory=new LogEventFactory();
		Settings settings=new Settings();
		Validator validator=new Validator(td, modelConfig,logger,errFactory,settings);
		validator.validate(new ObjectEvent(objMaxLength));
		// Asserts
		assertTrue(logger.getErrs().size()==1);
		assertEquals("Attribute mtextLimited is length restricted to 10", logger.getErrs().get(0).getEventMsg());
	}
	
	@Test
	public void coordType4Dimensions(){
		Iom_jObject objWrongFormat=new Iom_jObject("Datatypes23.Topic.ClassA", "o1");
		IomObject coordValue=objWrongFormat.addattrobj("hcoord", "COORD");
		coordValue.setattrvalue("C1", "480000.000");
		coordValue.setattrvalue("C2", "70000.000");
		coordValue.setattrvalue("C3", "6.000");
		coordValue.setattrvalue("C4", "2.000");
		ValidationConfig modelConfig=new ValidationConfig();
		LogCollector logger=new LogCollector();
		LogEventFactory errFactory=new LogEventFactory();
		Settings settings=new Settings();
		Validator validator=new Validator(td, modelConfig,logger,errFactory,settings);
		validator.validate(new ObjectEvent(objWrongFormat));
		// Asserts
		assertTrue(logger.getErrs().size()==1);
	}
	
	@Test
	public void coordType3DMissingC3(){
		Iom_jObject objWrongFormat=new Iom_jObject("Datatypes23.Topic.ClassA", "o1");
		IomObject coordValue=objWrongFormat.addattrobj("hcoord", "COORD");
		coordValue.setattrvalue("C1", "480000.000");
		coordValue.setattrvalue("C2", "70000.000");
		ValidationConfig modelConfig=new ValidationConfig();
		LogCollector logger=new LogCollector();
		LogEventFactory errFactory=new LogEventFactory();
		Settings settings=new Settings();
		Validator validator=new Validator(td, modelConfig,logger,errFactory,settings);
		validator.validate(new ObjectEvent(objWrongFormat));
		// Asserts
		assertTrue(logger.getErrs().size()==1);
		assertEquals("Wrong COORD structure, C3 expected", logger.getErrs().get(0).getEventMsg());
	}
	
}