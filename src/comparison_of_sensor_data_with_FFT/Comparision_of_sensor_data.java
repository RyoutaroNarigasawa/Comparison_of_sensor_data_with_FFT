package comparison_of_sensor_data_with_FFT;
import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import FFT.FFT4g;

public class Comparision_of_sensor_data {
	public static void main(String[] args) throws IOException {
		// TODO 自動生成されたメソッド・スタブ
		ArrayList<Double> before_valueA_A_x_axis = new ArrayList<Double>(1);//正規化前の実験データ保存用A
		ArrayList<Double> before_valueA_A_y_axis = new ArrayList<Double>(1);
		ArrayList<Double> before_valueA_A_z_axis = new ArrayList<Double>(1);
		ArrayList<Double> before_valueA_G_x_axis = new ArrayList<Double>(1);
		ArrayList<Double> before_valueA_G_y_axis = new ArrayList<Double>(1);
		ArrayList<Double> before_valueA_G_z_axis = new ArrayList<Double>(1);

		ArrayList<Double> before_valueB_A_x_axis = new ArrayList<Double>(1);//正規化前の実験データ保存用B
		ArrayList<Double> before_valueB_A_y_axis = new ArrayList<Double>(1);
		ArrayList<Double> before_valueB_A_z_axis = new ArrayList<Double>(1);
		ArrayList<Double> before_valueB_G_x_axis = new ArrayList<Double>(1);
		ArrayList<Double> before_valueB_G_y_axis = new ArrayList<Double>(1);
		ArrayList<Double> before_valueB_G_z_axis = new ArrayList<Double>(1);

		ArrayList<Double> after_valueA_A_x_axis = new ArrayList<Double>(1);//正規化後の実験データ保存用A
		ArrayList<Double> after_valueA_A_y_axis = new ArrayList<Double>(1);
		ArrayList<Double> after_valueA_A_z_axis = new ArrayList<Double>(1);
		ArrayList<Double> after_valueA_G_x_axis = new ArrayList<Double>(1);
		ArrayList<Double> after_valueA_G_y_axis = new ArrayList<Double>(1);
		ArrayList<Double> after_valueA_G_z_axis = new ArrayList<Double>(1);

		ArrayList<Double> after_valueB_A_x_axis = new ArrayList<Double>(1);//正規化後の実験データ保存用B
		ArrayList<Double> after_valueB_A_y_axis = new ArrayList<Double>(1);
		ArrayList<Double> after_valueB_A_z_axis = new ArrayList<Double>(1);
		ArrayList<Double> after_valueB_G_x_axis = new ArrayList<Double>(1);
		ArrayList<Double> after_valueB_G_y_axis = new ArrayList<Double>(1);
		ArrayList<Double> after_valueB_G_z_axis = new ArrayList<Double>(1);
		
		
		double[] fft_after_valueA_A_x_axis = new double[512];//フーリエ級数の保存用A
		double[] fft_after_valueA_A_y_axis = new double[512];
		double[] fft_after_valueA_A_z_axis = new double[512];
		double[] fft_after_valueA_G_x_axis = new double[512];
		double[] fft_after_valueA_G_y_axis = new double[512];
		double[] fft_after_valueA_G_z_axis = new double[512];

		double[] fft_after_valueB_A_x_axis = new double[512];//フーリエ級数保存用B
		double[] fft_after_valueB_A_y_axis = new double[512];
		double[] fft_after_valueB_A_z_axis = new double[512];
		double[] fft_after_valueB_G_x_axis = new double[512];
		double[] fft_after_valueB_G_y_axis = new double[512];
		double[] fft_after_valueB_G_z_axis = new double[512];
		
		
		

		int i = 0;//ループカウンタ
		String filenameA; // input of filename
		String filenameB;
		BufferedReader readA = new BufferedReader(new InputStreamReader(System.in),1); 
		BufferedReader readB = new BufferedReader(new InputStreamReader(System.in),1); 
		Boolean A, B = true;

		before_valueA_A_x_axis.add(0.0);//正規化のために0番目に0を代入する
		before_valueA_A_y_axis.add(0.0);
		before_valueA_A_z_axis.add(0.0);
		before_valueA_G_x_axis.add(0.0);
		before_valueA_G_y_axis.add(0.0);
		before_valueA_G_z_axis.add(0.0);

		before_valueB_A_x_axis.add(0.0);
		before_valueB_A_y_axis.add(0.0);
		before_valueB_A_z_axis.add(0.0);
		before_valueB_G_x_axis.add(0.0);
		before_valueB_G_y_axis.add(0.0);
		before_valueB_G_z_axis.add(0.0);
		
		
		System.out.println("被験者と実験日時、持ち手、回転を入力してください(被験者実験日時持ち手回転)");
		System.out.flush();
		filenameA = readA.readLine();
		
		System.out.println("比較する被験者と実験日時、持ち手、回転を入力してください(被験者実験日時持ち手回転)");
		System.out.flush();
		filenameB = readB.readLine();

		try {
			FileInputStream fileinA = new FileInputStream("F:/Comparison_of_sensor_data/src/"+filenameA+".xlsx"); // ファイル読み込み
			XSSFWorkbook wbA = new XSSFWorkbook(fileinA); // ワークブックに取り込む
			XSSFSheet sheetA = wbA.getSheetAt(0); // シート０番を取りだす
			XSSFCell cellA_A_x_axis = null;
			XSSFCell cellA_A_y_axis = null;
			XSSFCell cellA_A_z_axis = null;
			XSSFCell cellA_G_x_axis = null;
			XSSFCell cellA_G_y_axis = null;
			XSSFCell cellA_G_z_axis = null;
			i = 0;
			while (A = true) {
				if (sheetA.getRow(i).getCell(0) == null) {
					A = false;
					break;
				}
				cellA_A_x_axis = sheetA.getRow(i).getCell(0); //　セル(0,0)＝A1を指定
				cellA_A_y_axis = sheetA.getRow(i).getCell(1);
				cellA_A_z_axis = sheetA.getRow(i).getCell(2);
				cellA_G_x_axis = sheetA.getRow(i).getCell(3);
				cellA_G_y_axis = sheetA.getRow(i).getCell(4);
				cellA_G_z_axis = sheetA.getRow(i).getCell(5);

				before_valueA_A_x_axis.add(cellA_A_x_axis.getNumericCellValue());
				before_valueA_A_y_axis.add(cellA_A_y_axis.getNumericCellValue());
				before_valueA_A_z_axis.add(cellA_A_z_axis.getNumericCellValue());
				before_valueA_G_x_axis.add(cellA_G_x_axis.getNumericCellValue());
				before_valueA_G_y_axis.add(cellA_G_y_axis.getNumericCellValue());
				before_valueA_G_z_axis.add(cellA_G_z_axis.getNumericCellValue());
				i++;
				
				
			}
			wbA.close();
			
		} catch (Exception e) {
			//e.printStackTrace();
			//System.out.println("Error Loading");
		}
		
	
		
		try {
			FileInputStream fileinB = new FileInputStream("F:/Comparison_of_sensor_data/src/"+filenameB+".xlsx"); // ファイル読み込み
			XSSFWorkbook wbB = new XSSFWorkbook(fileinB); // ワークブックに取り込む
			XSSFSheet sheetB = wbB.getSheetAt(0); // シート０番を取りだす
			XSSFCell cellB_A_x_axis = null;
			XSSFCell cellB_A_y_axis = null;
			XSSFCell cellB_A_z_axis = null;
			XSSFCell cellB_G_x_axis = null;
			XSSFCell cellB_G_y_axis = null;
			XSSFCell cellB_G_z_axis = null;
			i = 0;
			while (true) {
				if (sheetB.getRow(i).getCell(0) == null) {
					A = false;
					break;
				}
				cellB_A_x_axis = sheetB.getRow(i).getCell(0); //　セル(0,0)＝A1を指定
				cellB_A_y_axis = sheetB.getRow(i).getCell(1);
				cellB_A_z_axis = sheetB.getRow(i).getCell(2);
				cellB_G_x_axis = sheetB.getRow(i).getCell(3);
				cellB_G_y_axis = sheetB.getRow(i).getCell(4);
				cellB_G_z_axis = sheetB.getRow(i).getCell(5);

				before_valueB_A_x_axis.add(cellB_A_x_axis.getNumericCellValue());
				before_valueB_A_y_axis.add(cellB_A_y_axis.getNumericCellValue());
				before_valueB_A_z_axis.add(cellB_A_z_axis.getNumericCellValue());
				before_valueB_G_x_axis.add(cellB_G_x_axis.getNumericCellValue());
				before_valueB_G_y_axis.add(cellB_G_y_axis.getNumericCellValue());
				before_valueB_G_z_axis.add(cellB_G_z_axis.getNumericCellValue());

				i++;
			}
			for(int k = 0; k < before_valueB_A_x_axis.size(); k++){
				System.out.println(k+":"+before_valueB_A_x_axis.get(k));
			}

		} catch (Exception e) {
			//e.printStackTrace();
			//System.out.println("Error Loading");
		}

		Linear_interpolation linearA = new Linear_interpolation(before_valueA_A_x_axis, before_valueA_A_y_axis,
				before_valueA_A_z_axis, before_valueA_G_x_axis, before_valueA_G_y_axis, before_valueA_G_z_axis);
		Linear_interpolation linearB = new Linear_interpolation(before_valueB_A_x_axis, before_valueB_A_y_axis,
				before_valueB_A_z_axis, before_valueB_G_x_axis, before_valueB_G_y_axis, before_valueB_G_z_axis);
		linearA.linear_interpolation_calculate();
		linearB.linear_interpolation_calculate();
		after_valueA_A_x_axis = linearA.getValueA();
		after_valueA_A_y_axis = linearA.getValueB();
		after_valueA_A_z_axis = linearA.getValueC();
		after_valueA_G_x_axis = linearA.getValueD();
		after_valueA_G_y_axis = linearA.getValueE();
		after_valueA_G_z_axis = linearA.getValueF();

		after_valueB_A_x_axis = linearB.getValueA();
		after_valueB_A_y_axis = linearB.getValueB();
		after_valueB_A_z_axis = linearB.getValueC();
		after_valueB_G_x_axis = linearB.getValueD();
		after_valueB_G_y_axis = linearB.getValueE();
		after_valueB_G_z_axis = linearB.getValueF();
		
		FFT4g fft = new FFT4g(after_valueA_A_x_axis.size());
		
		for(i = 0;i < after_valueA_A_x_axis.size(); i++){
			fft_after_valueA_A_x_axis[i] = after_valueA_A_x_axis.get(i);
			fft_after_valueA_A_y_axis[i] = after_valueA_A_y_axis.get(i);
			fft_after_valueA_A_z_axis[i] = after_valueA_A_z_axis.get(i);
			fft_after_valueA_G_x_axis[i] = after_valueA_G_x_axis.get(i);
			fft_after_valueA_G_y_axis[i] = after_valueA_G_y_axis.get(i);
			fft_after_valueA_G_z_axis[i] = after_valueA_G_z_axis.get(i);
		}
		fft.rdft(1,fft_after_valueA_A_x_axis);
		fft.rdft(1,fft_after_valueA_A_y_axis);
		fft.rdft(1,fft_after_valueA_A_z_axis);
		fft.rdft(1,fft_after_valueA_G_x_axis);
		fft.rdft(1,fft_after_valueA_G_y_axis);
		fft.rdft(1,fft_after_valueA_G_z_axis);
		
		for(i = 0;i < after_valueB_A_x_axis.size(); i++){
			fft_after_valueB_A_x_axis[i] = after_valueB_A_x_axis.get(i); 
			fft_after_valueB_A_y_axis[i] = after_valueB_A_y_axis.get(i); 
			fft_after_valueB_A_z_axis[i] = after_valueB_A_z_axis.get(i); 
			fft_after_valueB_G_x_axis[i] = after_valueB_G_x_axis.get(i); 
			fft_after_valueB_G_y_axis[i] = after_valueB_G_y_axis.get(i); 
			fft_after_valueB_G_z_axis[i] = after_valueB_G_z_axis.get(i); 
		}
		fft.rdft(1,fft_after_valueB_A_x_axis);
		fft.rdft(1,fft_after_valueB_A_y_axis);
		fft.rdft(1,fft_after_valueB_A_z_axis);
		fft.rdft(1,fft_after_valueB_G_x_axis);
		fft.rdft(1,fft_after_valueB_G_y_axis);
		fft.rdft(1,fft_after_valueB_G_z_axis);
		
		

		Bhattachariya_coefficient Bhat = new Bhattachariya_coefficient(
				fft_after_valueA_A_x_axis,
				fft_after_valueA_A_y_axis,
				fft_after_valueA_A_z_axis,
				fft_after_valueA_G_x_axis,
				fft_after_valueA_G_y_axis,
				fft_after_valueA_G_z_axis,
				fft_after_valueB_A_x_axis,
				fft_after_valueB_A_y_axis,
				fft_after_valueB_A_z_axis,
				fft_after_valueB_G_x_axis,
				fft_after_valueB_G_y_axis,
				fft_after_valueB_G_z_axis);
		Bhat.caluculate();

	}

}