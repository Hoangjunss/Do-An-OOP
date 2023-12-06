import java.util.Scanner;

public class HoaDon {
	 private String maHD;
	    private String NgayLapHoaDon;
	    private String maKH;
	    private String maNV;
	   

		Scanner sc=new Scanner(System.in);
		public HoaDon(){}
    public HoaDon(String maHD, String NgayLapHoaDon, String maKH,String maNV){
        this.maHD =maHD;
        this.NgayLapHoaDon = NgayLapHoaDon;
        this.maKH = maKH;
        this.maNV = maNV;
       
        
    }
    public HoaDon(HoaDon HD){
        maHD =HD.maHD;
       NgayLapHoaDon= HD.NgayLapHoaDon;
        maKH = HD.maKH;
       maNV = HD.maNV;
     
       
    }

	    public String getmaHD(){
	        return this.maHD;
	    }
	    public void setmaHD(String maHD){
	        this.maHD=maHD;
	    }

	    public String getNgayLapHoaDon(){
	        return this.NgayLapHoaDon;
	    }
	    public void setNgayLapHoaDon(String NgayLapHoaDon){
	        this.NgayLapHoaDon=NgayLapHoaDon;
	    }

	    public String getmaKH(){
	        return this.maKH;
	    }
	    public void setmaKH(String maKH){
	        this.maKH=maKH;
	    }
	    public String getmaNV(){
	        return this.maNV;
	    }
	    public void setmaNV(String maNV){
	        this.maNV=maNV;
	    }

	   
		public void nhap(){
			System.out.println("***Nhap Thong Tin San Pham***");
			System.out.print("Ma Hoa DOn: "); maHD = sc.nextLine();
			System.out.print("NgayLapHoaDon: "); NgayLapHoaDon = sc.nextLine();
			System.out.print("ma Khach Hang: "); maKH = sc.nextLine();
			System.out.print("ma Nhan vien: "); maNV = sc.nextLine();
		
        
		}
		public void xuat(){
			System.out.println("--Thong Tin San Pham--");
			System.out.println("Ma: "+maHD);
			System.out.println("NgayLapHoaDon: "+NgayLapHoaDon);
			System.out.println("maKH: "+maKH);
			System.out.println("maNV: "+maNV);

       
		}
}
