/**
 * 
 */
package database.com;

/**
 * @author Sabuz
 *
 */

import com.myclass.AddAdvice;
import com.myclass.AddConversation;
import com.myclass.AddDisease;
import com.myclass.AddMedicine;
import com.myclass.AddTest;
import com.myclass.Appoinment;
import com.myclass.BloodTest;
import com.myclass.Message;
import com.myclass.MyPicture;
import com.myclass.Patient;
import com.myclass.UrineTest;
import com.myclass.VisitData;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.graphics.Picture;
import android.util.Log;

public class ShowDatabaseHelper extends SQLiteOpenHelper {
	static final String DBNAME = "DoctorPatientShowDatabase";

	static final String ID = "id";
	static final String DATE = "date";
	static final String TIME = "time";

	public static final String PATIENTTABLE = "patienttable";
	static final String PATIENT_NAME = "name";
	static final String PATIENT_EMAIL = "email";
	static final String PATIENT_MOBILE = "mobile";
	static final String PATIENT_AGE = "age";
	static final String PATIENT_BIRTHDATE = "birthdate";
	static final String PATIENT_SEX = "sex";
	static final String PATIENT_BLOODGROUP = "blood_group";
	static final String PATIENT_DUR_MONTH = "dur_month";
	static final String PATIENT_DUR_YEAR = "dur_year";
	static final String VISITCOUNT = "visit_count";

	static final String APPOINMENTTABLE = "appoinmenttable";

	static final String MESSAGETABLE = "messagetable";
	static final String PATIENT_ID = "patient_id";
	static final String MESSAGE = "message";

	static final String VISITID = "visit_id";

	static final String VISITDATATABLE = "visitdatatable";
	static final String PURPOSE = "purpose";
	static final String TEMPERATURE = "temperature";
	static final String WEIGHT = "weight";
	static final String BPH = "bph";
	static final String BPL = "bpl";
	static final String HRMIN = "hrmin";
	static final String HRMAX = "hrmax";

	static final String BLOODTESTTABLE = "bloodtesttable";
	static final String HEMOGLOBIN = "hemoglobin";
	static final String ESR = "esr";
	static final String WBC = "wbc";
	static final String NEUTROPHILIS = "neutrophilis";
	static final String LYMPHOCYTES = "lymphocytes";
	static final String MONOCYTES = "monocytes";
	static final String ASO = "aso";
	static final String CRP = "crp";

	static final String URINETESTTABLE = "urinetesttable";
	static final String GLUCOSE = "glucose";
	static final String WHITEBLOODCELLS = "white_blood_cells";
	static final String REDBLOODCELLS = "red_blood_cells";
	static final String PH = "pH";

	static final String VISITMEDICINETABLE = "visitmedicinetable";
	static final String MEDICINETABLE = "medicinetable";
	static final String MEDICINENAME = "medicinename";
	static final String MEDICINETIME = "medicinetime";
	static final String HOWLONG = "howlong";

	static final String DISEASETABLE = "desiesetable";
	static final String VISITDISEASETABLE = "visitdesiesetable";

	static final String DISEASENAME = "diseasename";

	static final String VISITTESTTABLE = "visittesttable";
	static final String TESTTABLE = "testtable";
	static final String TESTNAME = "testname";

	static final String VISITADVICETABLE = "visitadvicetable";
	static final String ADVICETABLE = "advicetable";
	static final String ADVICE = "advice";

	static final String VISITCONVERSATIONTABLE = "visitconversationtable";

	static final String VISITAUDIONOTETABLE = "visitaudionotetable";

	static final String VISITPICTURETABLE = "visitpicturetable";
	static final String COMMENT = "comment";

	static final String FILENAME = "filename";

	public ShowDatabaseHelper(Context context) {
		super(context, DBNAME, null, 33);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void onCreate(SQLiteDatabase db) {
		// TODO Auto-generated method stub

		db.execSQL("DROP TABLE if exists " + PATIENTTABLE);

		db.execSQL("CREATE TABLE " + PATIENTTABLE + " ( "//
				+ ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "//
				+ VISITCOUNT + " INT, "//
				+ PATIENT_NAME + " VARCHAR, "//
				+ PATIENT_EMAIL + " VARCHAR, "//
				+ PATIENT_MOBILE + " VARCHAR, "//
				+ PATIENT_AGE + " INT, "//
				+ PATIENT_BIRTHDATE + " VARCHAR, "//
				+ PATIENT_SEX + " VARCHAR, "//
				+ PATIENT_BLOODGROUP + " VARCHAR, "//
				+ DATE + " VARCHAR, "//
				+ PATIENT_DUR_MONTH + " INT, "//
				+ PATIENT_DUR_YEAR + " INT) ");

		db.execSQL("DROP TABLE if exists " + APPOINMENTTABLE);

		db.execSQL("CREATE TABLE " + APPOINMENTTABLE + " ( "//
				+ ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "//
				+ PATIENT_NAME + " VARCHAR, "//
				+ PATIENT_EMAIL + " VARCHAR, "//
				+ PATIENT_MOBILE + " VARCHAR, "//
				+ TIME + " VARCHAR, "//
				+ DATE + " VARCHAR) ");

		db.execSQL("DROP TABLE if exists " + MESSAGETABLE);

		db.execSQL("CREATE TABLE " + MESSAGETABLE + " ( "//
				+ ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "//
				+ PATIENT_ID + " INT, "//
				+ TIME + " VARCHAR, "//
				+ DATE + " VARCHAR, "//
				+ MESSAGE + " VARCHAR) ");

		db.execSQL("DROP TABLE if exists " + VISITDATATABLE);

		db.execSQL("CREATE TABLE " + VISITDATATABLE + " ( "//
				+ ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "//
				+ VISITID + " INT, "//
				+ PATIENT_ID + " INT, "//
				+ PURPOSE + " VARCHAR, "//
				+ DATE + " VARCHAR, "//
				+ TEMPERATURE + " VARCHAR, "//
				+ WEIGHT + " INT, "//
				+ BPH + " INT, "//
				+ BPL + " INT, "//
				+ HRMIN + " INT, "//
				+ HRMAX + " INT) ");

		db.execSQL("DROP TABLE if exists " + BLOODTESTTABLE);

		db.execSQL("CREATE TABLE " + BLOODTESTTABLE + " ( "//
				+ ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "//
				+ VISITID + " INT, "//
				+ PATIENT_ID + " INT, "//
				+ DATE + " VARCHAR, "//
				+ HEMOGLOBIN + " INT, "//
				+ ESR + " INT, "//
				+ WBC + " INT, "//
				+ NEUTROPHILIS + " INT, "//
				+ LYMPHOCYTES + " INT, "//
				+ MONOCYTES + " INT, "//
				+ ASO + " INT, "//
				+ CRP + " INT) ");

		db.execSQL("DROP TABLE if exists " + URINETESTTABLE);

		db.execSQL("CREATE TABLE " + URINETESTTABLE + " ( "//
				+ ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "//
				+ VISITID + " INT, "//
				+ PATIENT_ID + " INT, "//
				+ DATE + " VARCHAR, "//
				+ GLUCOSE + " INT, "//
				+ REDBLOODCELLS + " INT, "//
				+ WHITEBLOODCELLS + " INT, "//
				+ HEMOGLOBIN + " INT, "//
				+ PH + " INT) ");

		db.execSQL("DROP TABLE if exists " + VISITMEDICINETABLE);

		db.execSQL("CREATE TABLE " + VISITMEDICINETABLE + " ( "//
				+ ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "//
				+ VISITID + " INT, "//
				+ PATIENT_ID + " INT, "//
				+ DATE + " VARCHAR) ");

		db.execSQL("DROP TABLE if exists " + MEDICINETABLE);

		db.execSQL("CREATE TABLE " + MEDICINETABLE + " ( "//
				+ VISITID + " INT, "//
				+ PATIENT_ID + " INT, "//
				+ MEDICINENAME + " VARCHAR) ");

		db.execSQL("DROP TABLE if exists " + VISITDISEASETABLE);

		db.execSQL("CREATE TABLE " + VISITDISEASETABLE + " ( "//
				+ ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "//
				+ VISITID + " INT, "//
				+ PATIENT_ID + " INT, "//
				+ DATE + " VARCHAR) ");

		db.execSQL("DROP TABLE if exists " + DISEASETABLE);

		db.execSQL("CREATE TABLE " + DISEASETABLE + " ( "//
				+ VISITID + " INT, "//
				+ PATIENT_ID + " INT, "//
				+ DISEASENAME + " VARCHAR) ");

		db.execSQL("DROP TABLE if exists " + VISITTESTTABLE);

		db.execSQL("CREATE TABLE " + VISITTESTTABLE + " ( "//
				+ ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "//
				+ VISITID + " INT, "//
				+ PATIENT_ID + " INT, "//
				+ DATE + " VARCHAR) ");

		db.execSQL("DROP TABLE if exists " + TESTTABLE);

		db.execSQL("CREATE TABLE " + TESTTABLE + " ( "//
				+ VISITID + " INT, "//
				+ PATIENT_ID + " INT, "//
				+ TESTNAME + " VARCHAR) ");

		db.execSQL("DROP TABLE if exists " + VISITADVICETABLE);

		db.execSQL("CREATE TABLE " + VISITADVICETABLE + " ( "//
				+ ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "//
				+ VISITID + " INT, "//
				+ PATIENT_ID + " INT, "//
				+ DATE + " VARCHAR) ");

		db.execSQL("DROP TABLE if exists " + ADVICETABLE);

		db.execSQL("CREATE TABLE " + ADVICETABLE + " ( "//
				+ VISITID + " INT, "//
				+ PATIENT_ID + " INT, "//
				+ ADVICE + " VARCHAR) ");

		db.execSQL("DROP TABLE if exists " + VISITCONVERSATIONTABLE);

		db.execSQL("CREATE TABLE " + VISITCONVERSATIONTABLE + " ( "//
				+ ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "//
				+ VISITID + " INT, "//
				+ PATIENT_ID + " INT, "//
				+ DATE + " VARCHAR, "//
				+ FILENAME + " VARCHAR) ");

		db.execSQL("DROP TABLE if exists " + VISITPICTURETABLE);

		db.execSQL("CREATE TABLE " + VISITPICTURETABLE + " ( "//
				+ ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "//
				+ VISITID + " INT, "//
				+ PATIENT_ID + " INT, "//
				+ DATE + " VARCHAR, "//
				+ COMMENT + " VARCHAR, "//
				+ FILENAME + " VARCHAR) ");

		db.execSQL("DROP TABLE if exists " + VISITAUDIONOTETABLE);

		db.execSQL("CREATE TABLE " + VISITAUDIONOTETABLE + " ( "//
				+ ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "//
				+ VISITID + " INT, "//
				+ PATIENT_ID + " INT, "//
				+ DATE + " VARCHAR, "//
				+ FILENAME + " VARCHAR) ");

	}

	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
		// TODO Auto-generated method stub

	}

	public void Print_Message(String msg) {
		Log.d("MSG DB", msg);

	}

	public void Add_Patient(Patient p) {
		SQLiteDatabase db = this.getWritableDatabase();
		ContentValues cv = new ContentValues();
		cv.put(VISITCOUNT, p.visit_count);
		cv.put(PATIENT_NAME, p.Name);
		cv.put(PATIENT_EMAIL, p.Email);
		cv.put(PATIENT_MOBILE, p.Mobile);
		cv.put(PATIENT_AGE, p.Age);
		cv.put(PATIENT_BIRTHDATE, p.birthdate);
		cv.put(PATIENT_SEX, p.Sex);
		cv.put(PATIENT_BLOODGROUP, p.Blood_Group);
		cv.put(DATE, p.Added_Date);
		cv.put(PATIENT_DUR_MONTH, p.Dur_month);
		cv.put(PATIENT_DUR_YEAR, p.Dur_year);

		db.insert(PATIENTTABLE, PATIENT_NAME, cv);

		Print_Message("Successfully added patient");
		db.close();
	}

	public void Add_Appoinment(Appoinment p) {
		SQLiteDatabase db = this.getWritableDatabase();
		ContentValues cv = new ContentValues();
		cv.put(PATIENT_NAME, p.Name);
		cv.put(PATIENT_EMAIL, p.Email);
		cv.put(PATIENT_MOBILE, p.Mobile);
		cv.put(TIME, p.Time);
		cv.put(DATE, p.Date);

		db.insert(APPOINMENTTABLE, PATIENT_NAME, cv);
		Print_Message("Successfully added appoinment");
		db.close();
	}

	public void Add_Message(Message M) {
		SQLiteDatabase db = this.getWritableDatabase();
		ContentValues cv = new ContentValues();
		cv.put(PATIENT_ID, M.pateint_id);
		cv.put(TIME, M.time);
		cv.put(DATE, M.date);
		cv.put(MESSAGE, M.text);
		db.insert(MESSAGETABLE, PATIENT_ID, cv);
		Print_Message("Successfully added Message");
		db.close();
	}

	public void Add_VisitData(VisitData vd) {
		SQLiteDatabase db = this.getWritableDatabase();
		ContentValues cv = new ContentValues();

		cv.put(PATIENT_ID, vd.patient_id);
		cv.put(VISITID, vd.visit_id);
		cv.put(PURPOSE, vd.purpose_visit);
		cv.put(DATE, vd.Date);
		cv.put(TEMPERATURE, vd.temperature);
		cv.put(WEIGHT, vd.weight);
		cv.put(BPL, vd.bp_low);
		cv.put(BPH, vd.bp_high);
		cv.put(HRMIN, vd.heart_rate_min);
		cv.put(HRMAX, vd.heart_rate_max);

		db.insert(VISITDATATABLE, PATIENT_ID, cv);
		db.close();
	}

	public void Add_BloodTest(BloodTest bt) {
		SQLiteDatabase db = this.getWritableDatabase();
		ContentValues cv = new ContentValues();

		cv.put(PATIENT_ID, bt.patient_id);
		cv.put(VISITID, bt.visit_id);
		cv.put(DATE, bt.date);
		cv.put(HEMOGLOBIN, bt.Hemoglobin);
		cv.put(ESR, bt.ESR);
		cv.put(WBC, bt.WBC);
		cv.put(NEUTROPHILIS, bt.Neutrophilis);
		cv.put(LYMPHOCYTES, bt.Lymphocytes);
		cv.put(MONOCYTES, bt.Monocytes);
		cv.put(ASO, bt.ASO);
		cv.put(CRP, bt.CRP);

		db.insert(BLOODTESTTABLE, PATIENT_ID, cv);
		db.close();
	}

	public void Add_UrineTest(UrineTest ut) {
		SQLiteDatabase db = this.getWritableDatabase();
		ContentValues cv = new ContentValues();

		cv.put(PATIENT_ID, ut.patient_id);
		cv.put(VISITID, ut.visit_id);
		cv.put(DATE, ut.Date);
		cv.put(GLUCOSE, ut.Glucose);
		cv.put(REDBLOODCELLS, ut.Red_Blood_Cells);
		cv.put(WHITEBLOODCELLS, ut.White_Blood_Cells);
		cv.put(HEMOGLOBIN, ut.Hemoglobin);
		cv.put(PH, ut.pH);

		db.insert(URINETESTTABLE, PATIENT_ID, cv);
		db.close();
	}

	public void Add_Medicine(AddMedicine am) {
		SQLiteDatabase db = this.getWritableDatabase();
		ContentValues cv = new ContentValues();

		cv.put(VISITID, am.visit_id);
		cv.put(PATIENT_ID, am.patient_id);
		cv.put(DATE, am.Date);

		db.insert(VISITMEDICINETABLE, PATIENT_ID, cv);

		ContentValues cv1 = new ContentValues();

		for (int i = 0; i < am.medicinelist.size(); i++) {
			cv1.put(PATIENT_ID, am.patient_id);
			cv1.put(VISITID, am.visit_id);
			cv1.put(MEDICINENAME, am.medicinelist.get(i));

			db.insert(MEDICINETABLE, PATIENT_ID, cv1);
		}
		Print_Message("Successfully added Medicine" + am.Date);
		db.close();
	}

	public void Add_Disease(AddDisease ad) {
		SQLiteDatabase db = this.getWritableDatabase();

		ContentValues cv = new ContentValues();

		cv.put(VISITID, ad.visit_id);
		cv.put(PATIENT_ID, ad.patient_id);
		cv.put(DATE, ad.date);

		db.insert(VISITDISEASETABLE, PATIENT_ID, cv);

		ContentValues cv1 = new ContentValues();

		for (int i = 0; i < ad.diseaselist.size(); i++) {
			cv1.put(PATIENT_ID, ad.patient_id);
			cv1.put(VISITID, ad.visit_id);
			cv1.put(DISEASENAME, ad.diseaselist.get(i));

			db.insert(DISEASETABLE, PATIENT_ID, cv1);
		}
		db.close();
	}

	
	public void Add_VisitDisease(AddDisease ad) {
		SQLiteDatabase db = this.getWritableDatabase();

		ContentValues cv = new ContentValues();

		cv.put(VISITID, ad.visit_id);
		cv.put(PATIENT_ID, ad.patient_id);
		cv.put(DATE, ad.date);

		db.insert(VISITDISEASETABLE, PATIENT_ID, cv);

		
		db.close();
	}
	
	
	
	public void Add_VisitDiseaseName(AddDisease ad) {
		SQLiteDatabase db = this.getWritableDatabase();

		ContentValues cv = new ContentValues();

		cv.put(PATIENT_ID, ad.patient_id);
		cv.put(VISITID, ad.visit_id);
		cv.put(DISEASENAME, ad.diseasename);

		db.insert(DISEASETABLE, PATIENT_ID, cv);

		
		db.close();
	}
	
	public void _VisitDiseaseName(AddDisease ad) {
		SQLiteDatabase db = this.getWritableDatabase();

		ContentValues cv = new ContentValues();

		cv.put(PATIENT_ID, ad.patient_id);
		cv.put(VISITID, ad.visit_id);
		cv.put(DISEASENAME, ad.diseasename);

		db.insert(DISEASETABLE, PATIENT_ID, cv);

		
		db.close();
	}
	
	
	
	public void Add_Test(AddTest at) {
		SQLiteDatabase db = this.getWritableDatabase();

		ContentValues cv = new ContentValues();

		cv.put(VISITID, at.visit_id);
		cv.put(PATIENT_ID, at.patient_id);
		cv.put(DATE, at.Date);

		db.insert(VISITTESTTABLE, PATIENT_ID, cv);

		ContentValues cv1 = new ContentValues();

		for (int i = 0; i < at.testnamelist.size(); i++) {
			cv1.put(PATIENT_ID, at.patient_id);
			cv1.put(VISITID, at.visit_id);
			cv1.put(TESTNAME, at.testnamelist.get(i));

			db.insert(TESTTABLE, PATIENT_ID, cv1);
		}

		Print_Message("Successfully added Test" + at.Date);
		db.close();
	}

	public void Add_Advice(AddAdvice ad) {
		SQLiteDatabase db = this.getWritableDatabase();

		ContentValues cv = new ContentValues();

		cv.put(VISITID, ad.visit_id);
		cv.put(PATIENT_ID, ad.patient_id);
		cv.put(DATE, ad.Date);

		db.insert(VISITADVICETABLE, PATIENT_ID, cv);

		ContentValues cv1 = new ContentValues();

		for (int i = 0; i < ad.advicelist.size(); i++) {
			cv1.put(PATIENT_ID, ad.patient_id);
			cv1.put(VISITID, ad.visit_id);
			cv1.put(ADVICE, ad.advicelist.get(i));

			db.insert(ADVICETABLE, PATIENT_ID, cv1);
		}

		Print_Message("Successfully added Advice");
		db.close();
	}

	public void Add_ConVersation(AddConversation ad) {
		SQLiteDatabase db = this.getWritableDatabase();

		ContentValues cv = new ContentValues();

		cv.put(VISITID, ad.visit_id);
		cv.put(PATIENT_ID, ad.patient_id);
		cv.put(DATE, ad.Date);
		cv.put(FILENAME, ad.filename);
		db.insert(VISITCONVERSATIONTABLE, PATIENT_ID, cv);

		Print_Message("Successfully Added Conversaton");
		db.close();
	}

	public void Add_Picture(MyPicture ap) {
		SQLiteDatabase db = this.getWritableDatabase();

		ContentValues cv = new ContentValues();

		cv.put(VISITID, ap.visit_id);
		cv.put(PATIENT_ID, ap.patient_id);
		cv.put(DATE, ap.Date);
		cv.put(COMMENT, ap.comment);
		cv.put(FILENAME, ap.filename);
		db.insert(VISITPICTURETABLE, PATIENT_ID, cv);

		Print_Message("Successfully Added Picture");
		db.close();
	}

	public void Add_AudoNote(AddConversation ad) {
		SQLiteDatabase db = this.getWritableDatabase();

		ContentValues cv = new ContentValues();

		cv.put(VISITID, ad.visit_id);
		cv.put(PATIENT_ID, ad.patient_id);
		cv.put(DATE, ad.Date);
		cv.put(FILENAME, ad.filename);
		db.insert(VISITAUDIONOTETABLE, PATIENT_ID, cv);

		Print_Message("Successfully Added Audio Note ");
		db.close();
	}

	public Cursor GetPatientList() {
		SQLiteDatabase db = this.getReadableDatabase();
		Cursor c = db.rawQuery("SELECT *  FROM " + PATIENTTABLE, null);
		return c;
	}

	public Cursor GetPatientListbyName(String name) {
		SQLiteDatabase db = this.getReadableDatabase();
		try
		{
		Cursor c = db.rawQuery("SELECT *  FROM " + PATIENTTABLE +" WHERE "+PATIENT_NAME+" = '"+name+"'", null);
		return c;
		}
		catch(Exception E)
		{
			Print_Message(E.toString());
			Print_Message("NULL IN FIND NAME");
			
		}
		return null;
	}
	
	public Cursor GetPatientListbyAge(int age) {
		SQLiteDatabase db = this.getReadableDatabase();
		try
		{
		Cursor c = db.rawQuery("SELECT *  FROM " + PATIENTTABLE +" WHERE "+PATIENT_AGE+" = "+age, null);
		return c;
		}
		catch(Exception E)
		{
			Print_Message(E.toString());
			Print_Message("NULL IN FIND NAME");
			
		}
		return null;
	}
	

	public Cursor GetPatientListbyDate(String date) {
		SQLiteDatabase db = this.getReadableDatabase();
		try
		{
		Cursor c = db.rawQuery("SELECT *  FROM " + PATIENTTABLE +" WHERE "+DATE+" = '"+date+"'", null);
		return c;
		}
		catch(Exception E)
		{
			Print_Message(E.toString());
			Print_Message("NULL IN FIND NAME");
			
		}
		return null;
	}
	public Cursor GetAppoinmentList() {
		SQLiteDatabase db = this.getReadableDatabase();
		Cursor c = db.rawQuery("SELECT *  FROM " + APPOINMENTTABLE, null);
		return c;
	}

	public void Update_AppoinmentDate(int id, String date) {
		SQLiteDatabase db = this.getReadableDatabase();
		String strFilter = ID + "=" + id;
		ContentValues args = new ContentValues();
		args.put(DATE, date);
		db.update(APPOINMENTTABLE, args, strFilter, null);
		db.close();

	}

	public void Update_PatientInfo(Patient p) {
		SQLiteDatabase db = this.getReadableDatabase();
		String strFilter = ID + "=" + p.Patient_id;
		ContentValues cv = new ContentValues();
		cv.put(PATIENT_NAME, p.Name);
		
	
		cv.put(PATIENT_EMAIL, p.Email);
		cv.put(PATIENT_MOBILE, p.Mobile);
		cv.put(PATIENT_AGE, p.Age);
		cv.put(PATIENT_BIRTHDATE, p.birthdate);
		cv.put(PATIENT_SEX, p.Sex);
		cv.put(PATIENT_BLOODGROUP, p.Blood_Group);
		cv.put(DATE, p.Added_Date);
		cv.put(PATIENT_DUR_MONTH, p.Dur_month);
		cv.put(PATIENT_DUR_YEAR, p.Dur_year);
            
		db.update(PATIENTTABLE, cv, strFilter, null);
		Print_Message("Update Patient "+p.Patient_id+p.Name);
		db.close();

	}

	
	public void Update_PatientVisitCount(int id, int count) {
		SQLiteDatabase db = this.getReadableDatabase();
		String strFilter = ID + "=" + id;
		ContentValues args = new ContentValues();
		args.put(VISITCOUNT, count);
		db.update(PATIENTTABLE, args, strFilter, null);
		db.close();

	}

	public void Update_AppoinmentTime(int id, String time) {
		SQLiteDatabase db = this.getReadableDatabase();
		String strFilter = ID + "=" + id;
		ContentValues args = new ContentValues();
		args.put(TIME, time);
		db.update(APPOINMENTTABLE, args, strFilter, null);
		db.close();

	}

	public void Delete_Appoinment(int id) {
		SQLiteDatabase db = this.getWritableDatabase();
		String strFilter = ID + " = ?";
		String[] idlist = { String.valueOf(id) };
		int temp = db.delete(APPOINMENTTABLE, strFilter, idlist);
		if (temp > 0) {
			Print_Message(id + " Successfully Deleted " + temp);
		} else {
			Print_Message(id + " Can not deleted " + temp);
		}
		db.close();

	}

	public void Delete_Patient(int id) {
		SQLiteDatabase db = this.getWritableDatabase();
		String strFilter = ID + " = ?";
		String[] idlist = { String.valueOf(id) };
		int temp = db.delete(PATIENTTABLE, strFilter, idlist);
		if (temp > 0) {
			Print_Message(id + " Successfully Deleted " + temp);
		} else {
			Print_Message(id + " Can not deleted " + temp);
		}
		db.close();

	}
	
	public Cursor GetMessagetListByPatientId(int id) {
		SQLiteDatabase db = this.getReadableDatabase();

		Cursor c = db.rawQuery("SELECT *  FROM " + MESSAGETABLE + " WHERE "
				+ PATIENT_ID + " = " + id, null);
		return c;
	}

	public Cursor GetVisitDataListByPatientId(int id) {
		SQLiteDatabase db = this.getReadableDatabase();

		Cursor c = db.rawQuery("SELECT *  FROM " + VISITDATATABLE + " WHERE "
				+ PATIENT_ID + " = " + id, null);
		return c;
	}

	public Cursor GetBloodTestListByPatientId(int id) {
		SQLiteDatabase db = this.getReadableDatabase();

		Cursor c = db.rawQuery("SELECT *  FROM " + BLOODTESTTABLE + " WHERE "
				+ PATIENT_ID + " = " + id, null);
		return c;
	}

	public Cursor GetUrineTestListByPatientId(int id) {
		SQLiteDatabase db = this.getReadableDatabase();

		Cursor c = db.rawQuery("SELECT *  FROM " + URINETESTTABLE + " WHERE "
				+ PATIENT_ID + " = " + id, null);
		return c;
	}

	public Cursor GetVisitListForDiseaseListByPatientId(int id) {
		SQLiteDatabase db = this.getReadableDatabase();

		Cursor c = db.rawQuery("SELECT *  FROM " + VISITDISEASETABLE
				+ " WHERE " + PATIENT_ID + " = " + id, null);
		return c;
	}

	public Cursor GetDiseaseListByPatientIdandVisitid(int p_id, int v_id) {
		SQLiteDatabase db = this.getReadableDatabase();

		Cursor c = db.rawQuery("SELECT *  FROM " + DISEASETABLE + " WHERE "
				+ PATIENT_ID + " = " + p_id + " AND " + VISITID + " = " + v_id,
				null);
		return c;
	}

	public Cursor GetVisitListForMedicineListByPatientId(int id) {
		SQLiteDatabase db = this.getReadableDatabase();

		Cursor c = db.rawQuery("SELECT *  FROM " + VISITMEDICINETABLE
				+ " WHERE " + PATIENT_ID + " = " + id, null);
		return c;
	}

	public Cursor GetMedicineListByPatientIdandVisitid(int p_id, int v_id) {
		SQLiteDatabase db = this.getReadableDatabase();

		Cursor c = db.rawQuery("SELECT *  FROM " + MEDICINETABLE + " WHERE "
				+ PATIENT_ID + " = " + p_id + " AND " + VISITID + " = " + v_id,
				null);
		return c;
	}

	public Cursor GetVisitListForTestListByPatientId(int id) {
		SQLiteDatabase db = this.getReadableDatabase();

		Cursor c = db.rawQuery("SELECT *  FROM " + VISITTESTTABLE + " WHERE "
				+ PATIENT_ID + " = " + id, null);
		return c;
	}

	public Cursor GetTestListByPatientIdandVisitid(int p_id, int v_id) {
		SQLiteDatabase db = this.getReadableDatabase();

		Cursor c = db.rawQuery("SELECT *  FROM " + TESTTABLE + " WHERE "
				+ PATIENT_ID + " = " + p_id + " AND " + VISITID + " = " + v_id,
				null);
		return c;
	}

	public Cursor GetVisitListForAdviceListByPatientId(int id) {
		SQLiteDatabase db = this.getReadableDatabase();

		Cursor c = db.rawQuery("SELECT *  FROM " + VISITADVICETABLE + " WHERE "
				+ PATIENT_ID + " = " + id, null);
		return c;
	}

	public Cursor GetAdviceListByPatientIdandVisitid(int p_id, int v_id) {
		SQLiteDatabase db = this.getReadableDatabase();

		Cursor c = db.rawQuery("SELECT *  FROM " + ADVICETABLE + " WHERE "
				+ PATIENT_ID + " = " + p_id + " AND " + VISITID + " = " + v_id,
				null);
		return c;
	}

	public Cursor GetAudioConversationListByPatientId(int id) {
		SQLiteDatabase db = this.getReadableDatabase();

		Cursor c = db.rawQuery("SELECT *  FROM " + VISITCONVERSATIONTABLE
				+ " WHERE " + PATIENT_ID + " = " + id, null);
		return c;
	}

	public Cursor GetPictureListByPatientId(int id) {
		SQLiteDatabase db = this.getReadableDatabase();

		Cursor c = db.rawQuery("SELECT *  FROM " + VISITPICTURETABLE
				+ " WHERE " + PATIENT_ID + " = " + id, null);
		return c;
	}

	public Cursor GetAudioNoteListByPatientId(int id) {
		SQLiteDatabase db = this.getReadableDatabase();

		Cursor c = db.rawQuery("SELECT *  FROM " + VISITAUDIONOTETABLE
				+ " WHERE " + PATIENT_ID + " = " + id, null);
		return c;
	}

	public void Update_PictureComment(int id, String comment) {
		SQLiteDatabase db = this.getReadableDatabase();
		String strFilter = ID + "=" + id;
		ContentValues args = new ContentValues();
		args.put(COMMENT, comment);
		db.update(VISITPICTURETABLE, args, strFilter, null);
		db.close();

	}

}
