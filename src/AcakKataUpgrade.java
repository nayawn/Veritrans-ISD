import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;
import java.util.Scanner;

/*
 * Nayana Taradhanti Hodi
 * Faculty of Computer Science
 * University of Indonesia
 * Kelas untuk game acak kata
 * ver 1.2
 */
public class AcakKataUpgrade {
	public static void main(String[] args) throws IOException{
		
		Scanner in = new Scanner(System.in); 
		
		//cetak pilihan untuk bermain
		System.out.println("Selamat datang di game acak kata");
		System.out.println("Pilihlah salah satu opsi dibawah ini (1 atau 2): ");
		System.out.println("1. Bermain dengan list kata yang sudah ada");
		System.out.println("2. Membuat list kata baru");
		
		String fileList = "";
		boolean salahPilih = true;
		
		//memilih pilihan
		while(salahPilih){
			int pilihan = in.nextInt();
			
			//jika ingin memainkan list file yang sudah ada
			if(pilihan == 1){
				System.out.println("\n" +  "Masukkan nama file list kata yang sudah pernah anda buat");
				System.out.println("(list kata yang sudah disediakan bernama kataUmum) : ");
				fileList = in.next()  + ".txt";
				
				File fileBaru = new File(fileList);
				//handle case file belum ada
				if(fileBaru.exists()){
					salahPilih = false;
				} else {
					System.out.println("File yang anda maksud belum ada :( \n");
					System.out.println("Pilihlah salah satu opsi dibawah ini (1 atau 2): ");
					System.out.println("1. Bermain dengan list kata yang sudah ada");
					System.out.println("2. Membuat list kata baru");
				}
			} 
				//jika ingin membuat list baru
			else if(pilihan == 2){
				fileList = buatList();
				salahPilih = false;
			} 
			
			//jika memilih angka diluar opsi	
			else if(salahPilih){
				System.out.println("opsi yang anda pilih salah :( \n");
				System.out.println("Pilihlah salah satu opsi dibawah ini (1 atau 2): ");
				System.out.println("1. Bermain dengan list kata yang sudah ada");
				System.out.println("2. Membuat list kata baru");
			}
			
		}
		
		//menyimpan list kata
		 ArrayList<String> listKata = storeKata(fileList);
		 
		 //random kata yang akan muncul
		 Random random = new Random();
		 int idx = random.nextInt(listKata.size());
		 String kata = listKata.get(idx);
		 
		 //mencetak kata yang harus ditebak
		 System.out.print("Tebak kata: ");
		 String acakKata = shuffle(kata);
		 System.out.println(acakKata);
		 boolean jawabanSalah = true;
		 
		 //selama masih salah akan terus diminta jawabannya
		 while(jawabanSalah){
			 System.out.print("Jawab: ");
			 String tebakan = in.next();
			 
			 //jika benar, game selesai
			 if((tebakan.toLowerCase()).equals(kata)){
				 System.out.println("SELAMAT JAWABAN ANDA BENAR");
				 jawabanSalah = false;
			 } else {
				 System.out.println("Jawaban anda masih salah, Silakan coba lagi \n");
			 }
		 }
		 
	}
	
	/**
	 * Memasukkan nama file yang diinginkan
	 * @return String nama File
	 * @throws FileNotFoundException
	 * @throws UnsupportedEncodingException
	 */
	public static String buatList() throws FileNotFoundException, UnsupportedEncodingException {
		Scanner in = new Scanner(System.in);
		boolean exist = false;
		String namaFile = ""; 
		
		//jika nama file belum ada akan ditanyakan lagi
		while(!exist){
			System.out.println("\n" + "Masukkan nama list yang anda inginkan: ");
			namaFile = in.next() + ".txt";
			
			File fileBaru = new File(namaFile);
			
			//handle jika nama file sudah ada
			if(!fileBaru.exists()){
				exist = true;
			} else {
				System.out.println("Nama yang anda masukkan sudah ada");
			}
		}
		buatFile(namaFile);
		
		return namaFile;
	}

	/**
	 * Method untuk membuat file list kata
	 * @param String nama file
	 * @throws FileNotFoundException
	 * @throws UnsupportedEncodingException
	 */
	private static void buatFile(String nama) throws FileNotFoundException, UnsupportedEncodingException {
		Scanner in = new Scanner(System.in);
		PrintWriter writer = new PrintWriter(nama, "UTF-8");
		
		//memasukkan banyak kata yang diinginkan
		System.out.println("Banyak kata yang ingin anda masukkan: ");
		int banyakKata = in.nextInt();
		
		//membuat file yang berisi kata yang diinginkan
		System.out.println("Masukkan Kata yang anda inginkan (1 kata per baris): ");
		for(int i = 0; i < banyakKata; i++){
			String kataBaru = (in.next()).toLowerCase();
			writer.println(kataBaru);
		}
		
		System.out.println("Selamat! list kata anda sudah terbuat, selamat bermain :) \n");
		writer.close();
	}

	/**
	 * Method yang memasukkan kata dari file kedalam arraylist
	 * @param String data dari nama file 
	 * @return Arraylist data
	 * @throws IOException
	 */
	public static ArrayList<String> storeKata(String data) throws IOException{
		
		//baca file
		BufferedReader read = new BufferedReader(new FileReader(data));
		
		//arrayList kata
		ArrayList<String> kata = new ArrayList<String>();
		
		String baca = read.readLine();
		//store kata ke dalam arrayList
		while(baca != null){
			kata.add(baca);
			baca = read.readLine();
		}
		
		return kata;
	}
	
	/**
	 * Method ini akan mengacak kata yang dimasukkan
	 * @param String kata yang akan diacak
	 * @return kata yang sudah teracak
	 */
	public static String shuffle(String kata){
		//arrayList huruf
		ArrayList<Character> huruf = new ArrayList<Character>();
				
		//store huruf ke dalam arraylist
		for(int i = 0; i < kata.length(); i++){
			huruf.add(kata.charAt(i));
		}
		
		//randomize
		Collections.shuffle(huruf);
		
		//membuat String kata yang telah dirandom
		String hasilShuffle = "";
		for(int i = 0; i < huruf.size(); i++){
			hasilShuffle += huruf.get(i);
		}
		
		return hasilShuffle;
	}
	
}
