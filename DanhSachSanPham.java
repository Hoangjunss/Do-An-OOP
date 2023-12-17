
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.EOFException;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;
import java.util.Scanner;

public class DanhSachPhieuNhap {
    private int n, a=0;
    private  ChiTietPhieuNhap[] dspn = new ChiTietPhieuNhap[0];

    Scanner sc = new Scanner(System.in);

    public DanhSachPhieuNhap(){
    }
    public DanhSachPhieuNhap(int n, ChiTietPhieuNhap[] dspn){
        this.n = n;
        this.dspn = dspn;
    }
    public DanhSachPhieuNhap(DanhSachPhieuNhap dspn){
        this.n=dspn.n;
        this.dspn=dspn.dspn;
    }

    public void nhap() {
        System.out.print("--Nhap So Luong Phieu Nhap: ");
        this.n = sc.nextInt();
        sc.nextLine(); // Loại bỏ ký tự xuống dòng
        dspn = new ChiTietPhieuNhap[n];
    
        System.out.println("--Nhap Thong Tin Phieu Nhap:");
        for (int i = 0; i < dspn.length; i++) {
            System.out.println("\t\t\t\t\tPHIEU NHAP THU " + (i + 1));
            dspn[i] = new ChiTietPhieuNhap(); // Khởi tạo phần tử mảng
            dspn[i].nhap();
            ghiFileJava("PhieuNhap.txt");
            if (i > 0) {
                thayMaPN(i);
            }
        }
    }
    

    public void xuat(){
        System.out.println("\t\t\t\t\t\t\t=====DANH SACH PHIEU NHAP=====");
        System.out.println("==============================================================================================================================================================");
        System.out.format("|| %4s | %8s | %12s | %8s | %8s | %8s | %45s | %10s | %10s | %12s ||\n", "STT", "MaPN" , "Ngay" , "MaNV" , "MaNCC" , "MaSP" , "TenSP" , "So Luong" , "Don Gia" , "Thanh Tien" );
        try {
            //docFileJava("PhieuNhap.txt");
			for(int i = 0; i < n; i++) {
				System.out.format("|| %4s |", (i + 1));
                dspn[i].xuat();
			}
		}catch(NullPointerException npe) {}
        System.out.println("==============================================================================================================================================================");

    }

//KIỂM TRA VÀ THAY THẾ NẾU MAPN BỊ TRÙNG
    public void thayMaPN(int i){
        String maPN = dspn[i].getMaPN();
        String maDoi = "";
        do{
            if(kiemTraMaPN(maPN, i)){
                System.out.println();
                xuat();
                System.out.println("\nNha Cung Cap Thu "+ (i+1)+ " Co MA PHIEU NHAP Bi Trung. Nhan ENTER De Tiep Tuc!!!");
                sc.nextLine(); sc.nextLine();
                System.out.print("Nhap Lai Ma Phieu Nhap: ");
                maDoi = sc.nextLine();
                dspn[i].setMaPN(maDoi);
                ghiFileJava("PhieuNhap.txt");
                maPN = dspn[i].getMaPN();
            }
        }while (kiemTraMaPN(maPN, i));
    }

    public boolean kiemTraMaPN(String maPN, int k){
        docFileJava("PhieuNhap.txt");
        for(int i = a - 1; i>=0; i--){
            if(dspn[i].getMaPN().equals(maPN) && i!=k){
                return true;
            }
        }
        return false;
    }

    public boolean isValidDate(String inputDate){
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
        try {
            Date date = sdf.parse(inputDate);
            // Nếu chuyển đổi thành công, định dạng là hợp lệ
            return true;
        } catch (ParseException e) {
            // Nếu có lỗi, định dạng không hợp lệ
            return false;
        }
    }

    public void them(){
        System.out.print("So Luong Can Them: ");
        int sl = sc.nextInt();
        int j = n;
        int temp = n+sl;
        n+=sl;
        dspn =Arrays.copyOf(dspn,n);
        for(int i = j; i<temp; i++){
            System.out.println("\t\t\t\t\tPHIEU NHAP THU "+ (i+1));
            dspn[i] = new ChiTietPhieuNhap();
            dspn[i].nhap();
            ghiFileJava("PhieuNhap.txt");
            thayMaPN(i);
        }
        
    }

    public void xoa(){
        System.out.print("Nhap Ma Phieu Nhap Can Xoa: ");
        String maXoa = sc.nextLine();
        boolean daXoa = false; 
        for(int i=0; i<n; i++){
            if(dspn[i].getMaNCC().equals(maXoa)){
                for(int j=i; j<n-1;j++){
                    dspn[j] = new ChiTietPhieuNhap(dspn[j+1]);
                }
                dspn = Arrays.copyOf(dspn, n-1);
                daXoa = true;
                n--;
                ghiFileJava("PhieuNhap.txt");
                break;
            }
        }
        if(daXoa){
            System.out.println("Phieu Nhap Da Duoc Xoa!");
        }else{ 
            System.out.println("Khong Tim Thay Phieu Nhap Can Xoa!");
        }
    }

    public void sua(){
        int choice1 =0;
        do{
            System.out.print("\n--Nhap Ma Phieu Nhap Can Sua: ");
            String maSua = sc.next();
            ChiTietPhieuNhap pnCanSua = timKiem_Ma(maSua);
            if(pnCanSua != null){
                ChiTietPhieuNhap temp = new ChiTietPhieuNhap(timKiem_Ma(maSua));
                System.out.println("Thong Tin Phieu Nhap Can Sua:");
                System.out.println("==============================================================================================================================================================");
                System.out.format("|| %8s | %12s | %8s | %8s | %8s | %45s | %10s | %10s | %12s ||\n",  "MaPN" , "Ngay" , "MaNV" , "MaNCC" , "MaSP" , "TenSP" , "So Luong" , "Don Gia" , "Thanh Tien" );
                System.out.print("||");
                temp.xuat();
                System.out.println("==============================================================================================================================================================");
                System.out.println("Xac Nhan Dung Phieu Nhap Can Sua Khong! \n\t1:Dung \n\t2:Sai,Can Nhap Lai! \n\t0:Thoat");
                System.out.print("Lựa Chọn Của Bạn: ");
                choice1 = sc.nextInt();
                System.out.println();
                switch (choice1) {
                    case 1:
                        System.out.println("\t\tChon lua cach sua:");
                        System.out.println("\t1: Sua tat ca");
                        System.out.println("\t2: Sua tung dac diem");
                        System.out.println("\t0: Thoat");
                        System.out.print("Lua chon cua ban: ");
                        int choice2 = sc.nextInt();
                        choice1=0;
                        switch (choice2) {
                            case 1:
                                // Sua tat ca
                                pnCanSua.nhap(); // Nhap lai toan bo thong tin
                                ghiFileJava("NhaCungCap.txt");
                                thayMaPN(timKiem_Private(pnCanSua));
                                break;
        
                            case 2:
                                // Sua tung dac diem
                                suaTungDacDiem(pnCanSua);
                                break;
        
                            case 0:
                                return;
                            default:
                                System.out.println("Lua Chon Khong Hop Le!");
                                break;
                        }
                        break;
                    case 2:
                    
                        break;
                    case 0:
                        break;
                    default:
                        System.out.println("Lua Chon Khong Hop Le!");
                        break;
                }
            }else{
                System.out.println("Khong Tim Thay Nha Cung Cap Can Sua!");
            }
        }while(choice1 != 0);
    }

    private void suaTungDacDiem(ChiTietPhieuNhap pn) {
        int choice = 0;
        System.out.println("\t\tChon dac diem can sua:");
        System.out.println("\t1: Ma Phieu Nhap");
        System.out.println("\t2: Ngay Lap Phieu");
        System.out.println("\t3: Ma Nhan Vien Phu Trach");
        System.out.println("\t4: Ma Nha Cung Cap");
        System.out.println("\t5: Ma San Pham");
        System.out.println("\t6: Ten San Pham");
        System.out.println("\t7: So Luong San Pham");
        System.out.println("\t8: Don Gia Cua Moi San Pham");
        System.out.println("\t0: Ngung Thao Tac");
        System.out.print("Lua Chon: ");
        choice = sc.nextInt();

        switch (choice) {
            case 1:
                System.out.print("Nhap Ma Phieu Nhap Moi: ");
                sc.nextLine();
                pn.setMaPN(sc.nextLine());
                ghiFileJava("PhieuNhap.txt");
                thayMaPN(choice);
                break;

            case 2:
                String tpngay;
                do{
                    sc.nextLine();
                    System.out.print("Nhap vao ngay lap phieu(dd/MM/yyy): "); tpngay= sc.nextLine();
                    if(!isValidDate(tpngay)){
                        System.out.println("NHAP SAI DINH DANG 'dd/MM/yyyy'!!!\nNhan ENTER de nhap lai. ");
                        sc.nextLine();
                    }
                }while(!isValidDate(tpngay));
                pn.setNgay(tpngay);
                ghiFileJava("PhieuNhap.txt");
                break;
            case 3:
                System.out.print("Nhap Ma Nhan Vien Thay Moi: ");
                sc.nextLine();
                pn.setMaNV(sc.nextLine());
                ghiFileJava("PhieuNhap.txt");
                break;
            case 4:
                System.out.print("Nhap Ma Nha Cung Cap Moi: ");
                sc.nextLine();
                pn.setMaNCC(sc.nextLine());
                ghiFileJava("PhieuNhap.txt");
                break;
            case 5:
                System.out.print("Nhap Ma San Pham Moi: ");
                sc.nextLine();
                pn.setMaSP(sc.nextLine());
                ghiFileJava("PhieuNhap.txt");
                break;
            case 6:
                System.out.print("Nhap Ten San Pham Moi: ");
                sc.nextLine();
                pn.setTenSP(sc.nextLine());
                ghiFileJava("PhieuNhap.txt");
                break;
            case 7:
                System.out.print("Nhap So Luong San Pham Moi: ");
                pn.setSoluong(sc.nextInt());
                pn.setThanhTien(pn.getDonGia() * pn.getSoluong());
                ghiFileJava("PhieuNhap.txt");
                break;
            case 8:
                System.out.print("Nhap Don Gia Cua San Pham Moi: ");
                pn.setDongia(sc.nextLong());
                pn.setThanhTien(pn.getDonGia() * pn.getSoluong());
                ghiFileJava("PhieuNhap.txt");
                break;
            case 0:
                break;

            default:
                System.out.println("Lua Chon Khong Hop Le!");
                break;
        }
    }
//TÌM KIẾM
    public ChiTietPhieuNhap timKiem_Ma(String tkPN){
        int i=0;
        while(i<n){
            if(dspn[i].getMaPN().equals(tkPN)){
                break;
            }
            i++;
        }
        if(i>=n){
            System.out.println("Ma Phieu Nhap Sai Hoac Khong Ton Tai!");
            return null;
        }
        return dspn[i];
    }

    public void timKiem_Ma(){
        System.out.print("Nhap Ma Phieu Nhap Can Tim: ");
        String maSearch = sc.nextLine();
        int i=0,j=1;
        boolean flag = false;
        System.out.println("==============================================================================================================================================================");
        System.out.format("|| %4s | %8s | %12s | %8s | %8s | %8s | %45s | %10s | %10s | %12s ||\n", "STT", "MaPN" , "Ngay" , "MaNV" , "MaNCC" , "MaSP" , "TenSP" , "So Luong" , "Don Gia" , "Thanh Tien" );
        while(i<n){
            if(dspn[i].getMaPN().indexOf(maSearch) != -1){
                flag = true;
                try {
                    docFileJava("PhieuNhap.txt");
                    System.out.format("|| %4s |", (j));
                    j++;
                    dspn[i].xuat();
                }catch(NullPointerException npe) {}
            }
            i++;
        }
        System.out.println("==============================================================================================================================================================");
        if(flag == false){
            System.out.println("Ma Phieu Nhap Sai Hoac Khong Ton Tai!");
        }
    }

    public void timKiem_TenSP(){
        System.out.print("Nhap Ten San Pham Can Tim: ");
        String maSearch = sc.nextLine();
        int i=0,j=1;
        boolean flag = false;
        System.out.println("==============================================================================================================================================================");
        System.out.format("|| %4s | %8s | %12s | %8s | %8s | %8s | %45s | %10s | %10s | %12s ||\n", "STT", "MaPN" , "Ngay" , "MaNV" , "MaNCC" , "MaSP" , "TenSP" , "So Luong" , "Don Gia" , "Thanh Tien" );
        while(i<n){
            if(dspn[i].getTenSP().indexOf(maSearch) != -1){
                flag = true;
                try {
                    docFileJava("PhieuNhap.txt");
                    System.out.format("|| %4s |", (j));
                    j++;
                    dspn[i].xuat();
                }catch(NullPointerException npe) {}
            }
            
            i++;
        }
        System.out.println("==============================================================================================================================================================");
        if(flag == false){
            System.out.println("Ten San Pham Sai Hoac Khong Ton Tai!");
        }
    }

    public void timKiem_Ngay(){
        String maSearch;
        do{
            System.out.print("Nhap Ngay Lap Phieu Can Tim(dd/MM/yyy): ");
            maSearch = sc.nextLine();
            if(!isValidDate(maSearch)){
                System.out.println("NHAP SAI DINH DANG 'dd/MM/yyyy'!!!\nNhan ENTER de nhap lai. ");
                sc.nextLine();
            }
        }while(!isValidDate(maSearch));
        int i=0,j=1;
        boolean flag = false;
        System.out.println("==============================================================================================================================================================");
        System.out.format("|| %4s | %8s | %12s | %8s | %8s | %8s | %45s | %10s | %10s | %12s ||\n", "STT", "MaPN" , "Ngay" , "MaNV" , "MaNCC" , "MaSP" , "TenSP" , "So Luong" , "Don Gia" , "Thanh Tien" );
        while(i<n){
            if(dspn[i].getNgay().indexOf(maSearch) != -1){
                flag = true;
                try {
                    docFileJava("PhieuNhap.txt");
                    System.out.format("|| %4s |", (j));
                    j++;
                    dspn[i].xuat();
                }catch(NullPointerException npe) {}
            }
            
            i++;
        }
        System.out.println("==============================================================================================================================================================");
        if(flag == false){
            System.out.println("Khong Co Phieu Nhap Nao Duoc Lap Vao Ngay Ban Nhap!");
        }
    }

    private int timKiem_Private(ChiTietPhieuNhap tkPN){
        int i=0;
        while(i<n){
            if(dspn[i].getMaPN().equals(tkPN.getMaPN())){
                break;
            }
            i++;
        }
        return i;
    }

//MENU TÌM KIẾM
public void timKiem(){
    int select;
    do{
        select =4;
        System.out.println("\n\t\t\t\t\t======BANG LUA CHON======");
        System.out.println("\t\t\t\t\t1.An phim 1 de tim kiem phieu nhap theo ma");
        System.out.println("\t\t\t\t\t2.An phim 2 de tim kiem phieu nhap theo ten san pham");
        System.out.println("\t\t\t\t\t3.An phim 3 de tim kiem phieu nhap theo ngay lap phieu");
        System.out.println("\t\t\t\t\t4.An phim 4 de thoat");
        System.out.print("Lua chon ban la: ");
        select = sc.nextInt();
        sc.nextLine();
        switch(select) {
            case 1:
                timKiem_Ma();
                break;
            case 2:
                timKiem_TenSP();
                break;
            case 3:
                timKiem_Ngay();
                break;
            case 4: 
                return;
            default:
                System.err.println("Bạn đã nhập sai lựa chọn của mình. Xin mời vào lại chức năng!!!");
                break;
        }
    }while(select !=4 );
}

//THAO TAC
public void thaoTac(){
    int select = 0;
    docFileJava("PhieuNhap.txt");
    do{
        System.out.println("\t\t\t\t\t======BANG LUA CHON======");
        System.out.println("\t\t\t\t\t1.An phim 1 de them phieu nhap");
        System.out.println("\t\t\t\t\t2.An phim 2 de xoa phieu nhap");
        System.out.println("\t\t\t\t\t3.An phim 3 de sua thong tin phieu nhap");
        System.out.println("\t\t\t\t\t4.An phim 4 de tim kiem thong tin phieu nhap");
        System.out.println("\t\t\t\t\t5.An phim 5 de xuat danh sach phieu nhap");
        System.out.println("\t\t\t\t\t6.An phim 6 de xoa toan bo phieu nhap va nhap vao danh sach moi");
        System.out.println("\t\t\t\t\t7.An phim 7 de dung cac thao tac");
        System.out.print("\nLua chon cua ban la: ");
        select = sc.nextInt();
        sc.nextLine();
        switch(select) {
            case 1:
                them();
                xuat();
                break;
            case 2:
                xoa();
                xuat();
                break;
            case 3:
                sua();
                xuat();
                break;
            case 4:
                timKiem();
                break;
            case 5: 
                xuat();
                break;
            case 6:
                nhap();
                break;
            case 7:
                return;
            default:
                System.err.println("Bạn đã nhập sai lựa chọn");
                break;
            }
    }while(select != 7);
}

//GHI VÀ ĐỌC FILE
    public void ghiFileJava(String fileName){
        try {
            DataOutputStream dos = new DataOutputStream(new FileOutputStream(fileName));
            dos.writeInt((n));
            try {
                for(int i = 0; i<n; i++){
                    dspn[i].ghiFile(fileName);
                }
            } catch (NullPointerException e) {
                e.printStackTrace();
            }
            dos.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void docFileJava(String fileName){
        
        try {
            int i =0;
            DataInputStream dis = new DataInputStream(new FileInputStream(fileName));
            n = dis.readInt();
            dspn = new ChiTietPhieuNhap[n];
            try{
                while (true){
                    String maPN = dis.readUTF();
                    String ngay = dis.readUTF();
                    String maNV = dis.readUTF();
                    String maNCC = dis.readUTF();
                    String maSP = dis.readUTF();
                    String tenSP = dis.readUTF();
                    int soluong = dis.readInt();
                    long dongia = dis.readLong();
                    long thanhtien = dis.readLong();
                    dspn[i] = new ChiTietPhieuNhap(maPN, ngay, maNV, maNCC, maSP, tenSP, soluong, dongia, thanhtien);
                    i++;
                }
                
            }catch(EOFException ex){}
            finally{
                a = i;
                dis.close();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
