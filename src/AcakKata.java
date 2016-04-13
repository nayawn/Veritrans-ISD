import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;
import java.util.Scanner;

/*
 * Nayana Taradhanti Hodi
 * Faculty of Computer Science
 * University of Indonesia
 * Kelas untuk game acak kata
 */
public class AcakKata {
	public static void main(String[] args) throws IOException{
		
		//menyimpan list kata
		 ArrayList<String> listKata = storeKata("kataUmum.txt");
		 Scanner in = new Scanner(System.in); 
		 
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
			 String tebakan = in.nextLine();
			 
			 //jika benar, game selesai
			 if((tebakan.toLowerCase()).equals(kata)){
				 System.out.println("SELAMAT JAWABAN ANDA BENAR");
				 jawabanSalah = false;
			 } else {
				 System.out.println("Jawaban anda masih salah, Silakan coba lagi");
			 }
		 }
		 
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
