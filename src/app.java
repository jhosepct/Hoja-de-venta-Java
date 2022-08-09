import javafx.scene.control.cell.TextFieldTableCell;

import java.util.Scanner;
import java.util.*;
import java.util.Stack;
import java.util.Arrays;

public class app {
    public static void main(String[] args) {    // kevin 5
        Scanner entrada = new Scanner(System.in);
        int valor = 0;
        welcome();
        while(valor != 2){
            try{
                valor = menu();
                switch (valor){
                    case 1:
                        venta();
                        break;
                    case 2:
                        good_bye();
                        break;
                    default:
                        System.out.println("Digite 1 o 2");
                        break;
                }
            }catch(Exception e){
                System.out.println("Digite un número");
                entrada.next();
            }
        }

    }

    // frank
    public static int menu(){
        Scanner entrada = new Scanner(System.in);
        int valor;
        System.out.println("\n-------------------Menu-------------------");
        System.out.println("\n\t\t1. Venta | 2. Salir");
        System.out.print("> ");
        valor = entrada.nextInt();
        return valor;
    }
    public static void imprimir_factura(String usuario, String pedidos[][]){
        System.out.println("\nMonto a pagar de "+usuario);
        System.out.println("\nProducto\tPrecio\tCantidad\tSubtotal");

        for (int i = 0; i < pedidos.length; i++) {
            System.out.println(pedidos[i][0].toUpperCase()+"\t"+pedidos[i][1]+"\t"+pedidos[i][2]+"\t"+pedidos[i][3]);
        }

        double total = 0;
        for (int i = 0; i < pedidos.length; i++) {
            total += Double.parseDouble(pedidos[i][3]);
        }
        System.out.println("\nTotal más IGV: "+total*(1.18)); // 18% IGV
    }
    public static String validar_producto(String producto){
        String productos[][] = productos();
        String price = "";
        boolean validador = false;
        for (int i = 0; i < productos.length; i++) {
            if (producto.equalsIgnoreCase(productos[i][0])) {
                validador = true;
                price = productos[i][1];
                break;
            }
        }
        return validador ? price : null; //operador ternario
    }
    public static String[] agregar_pedido(){
        Scanner entrada = new Scanner(System.in);
        String producto;
        do{
            System.out.print("Ingrese el nombre del producto: ");
            producto = entrada.next();
        }while(validar_producto(producto) == null); //Valida que el producto exista
        System.out.print("Ingrese cantidad: ");
        double cantidad = entrada.nextDouble();
        double price = Double.parseDouble(validar_producto(producto)); //convertir string a double
        double sub_total = cantidad* price; //calcula el subtotal
        return new String[]{producto, Double.toString(price), Double.toString(cantidad), Double.toString(sub_total)};
    }

    // samira
    public static void bienvenida(String usuario){
        //Scanner entrada = new Scanner(System.in);
        System.out.println("\tBienvenido " + usuario + "!");
    }
    public static String[] usuarios(){
        String usuarios[] = new String[]{"user1", "user2", "user3", "user4"};
        return usuarios;
    }
    public static void imprimir_usuarios(){
        String usuarios[] = usuarios();
        for (int i = 0; i < usuarios.length; i++) {
            System.out.println((i+1)+") "+usuarios[i]);
        }
    }

    // cristian
    public static String[][] productos(){
        String productos[][] = new String[][]{
                {"Leche", "1.00"},
                {"Pan", "2.00"},
                {"Cereal", "3.00"},
                {"Jabon", "4.00"},
                {"Lavandina", "5.00"}
        };
        return productos;
    }
    public static void imprimir_productos(){
        String productos[][] = productos();
        for (int i = 0; i < productos.length; i++) {
            System.out.println("*"+productos[i][0]);
        }
    }
    public static boolean validar_usuario(String usuario){
        String usuarios[] = usuarios();
        boolean validador = false;
        for (int i = 0; i < usuarios.length; i++) {
            if (usuario.equals(usuarios[i])) {
                validador = true;
                break;
            }
        }
        return validador;
    }

    // Hemsy
    public static void welcome(){
        System.out.println("*************************************");
        System.out.println("*------------------------------------*");
        System.out.println("* ---     Bodega 'Doña Hemsy     --- *");
        System.out.println("*------------------------------------*");
        System.out.println("*************************************");
    }
    public  static void good_bye(){
        System.out.println("\nGracias por usar la aplicación de identificación de matrices");
        System.out.println("Este programa fue creado con cariño por:\n Cristian, Frank, Hemsy, Kevin y Samira");
    }

    // kevin
    public static void venta(){
        Scanner entrada = new Scanner(System.in);
        imprimir_usuarios();
        System.out.print("Ingrese usuario: ");
        String usuario = entrada.next();
        if(validar_usuario(usuario)){
            bienvenida(usuario); //Bienvenida al usuario
            imprimir_productos(); //imprime los productos
            // nombre precio cantidad subtotal
            // leche 1.50 2.00 3.00
            // pan 2.50 3.00 7.50
            // queso 3.00 4.00 12.00
            // leche 1.50 2.00 3.00
            // pan 2.50 3.00 7.50
            System.out.print("Ingrese la cantidad de productos: ");
            int cantidadProductos = entrada.nextInt();
            String pedidos[][]= new String[cantidadProductos][4];
            for (int j = 0; j < cantidadProductos; j++) {
                String pedido []=agregar_pedido();
                pedidos[j][0]=pedido[0];
                pedidos[j][1]=pedido[1];
                pedidos[j][2]=pedido[2];
                pedidos[j][3]=pedido[3];
            }
            imprimir_factura(usuario,pedidos);
            //System.out.println(pedidos);
        }
        else{
            System.out.println("Usuario no registrado");
        }
    }
}
