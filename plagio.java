import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.regex.Pattern;

public class plagio {
    
    public static String DetectorDePlagio(String ruta1,String ruta2) throws IOException{
        String text1 = ruta1;
        String text2 = ruta2;
        String resultado="";
        String[] words1 = preprocessAndSplit(text1);
        String[] words2 = preprocessAndSplit(text2);

        BST tree1 = buildTree(words1);
        BST tree2 = buildTree(words2);

        double similarity = calculateSimilarity(tree1, tree2);
        if(similarity>10){
            resultado="HUBO PLAGIO: Porcentaje de similitud: " + similarity + "%";
        }else{
            resultado="NO CONSIDERADO PLAGIO:Porcentaje de similitud: " + similarity + "%";
        }
        
        return resultado;
    }


    private static BST buildTree(String[] words) {
        // Construir un árbol de búsqueda binaria usando las palabras del array
        BST tree = new BST();
        for (String word : words) {
            tree.insert(word);
        }
        return tree;
    }

    private static double calculateSimilarity(BST tree1, BST tree2) {
        int commonNodes = countCommonNodes(tree1.root, tree2.root);
        int totalNodes = Math.min(countNodes(tree1.root), countNodes(tree2.root));
    
        double similarity = (double) commonNodes / totalNodes * 100.0;
        return similarity;
    }
    
    private static int countCommonNodes(TreeNode root1, TreeNode root2) {
        if (root1 == null || root2 == null) {
            return 0;
        }
    
        int count = 0;
        if (root1.word.equals(root2.word)) {
            count = 1;
        }
    
        count += countCommonNodes(root1.left, root2.left);
        count += countCommonNodes(root1.right, root2.right);
    
        return count;
    }
    
    private static int countNodes(TreeNode root) {
        if (root == null) {
            return 0;
        }
        return 1 + countNodes(root.left) + countNodes(root.right);
    }

    //prueba
     public static String[] preprocessAndSplit(String rutaArchivo) throws IOException {
        String contenidoCompleto = leerArchivo(rutaArchivo);
        String[] palabras = contenidoCompleto.split("\\s+"); // Dividir por espacios en blanco
        String[] palabrasFiltradas = filtrarCaracteres(palabras);

        return palabrasFiltradas;
    }

    public static String leerArchivo(String rutaArchivo) throws IOException {
        StringBuilder contenido = new StringBuilder();
        BufferedReader reader = new BufferedReader(new FileReader(rutaArchivo));

        String linea;
        while ((linea = reader.readLine()) != null) {
            contenido.append(linea).append("\n");
        }

        reader.close();
        return contenido.toString();
    }

    public static String[] filtrarCaracteres(String[] palabras) {
        Pattern patronCaracteresIndeseados = Pattern.compile("[.,\"\\(\\)]");
        String[] palabrasFiltradas = new String[palabras.length];
        int contador = 0;

        for (String palabra : palabras) {
            String palabraFiltrada = patronCaracteresIndeseados.matcher(palabra).replaceAll("");
            if (!palabraFiltrada.isEmpty()) {
                palabrasFiltradas[contador++] = palabraFiltrada;
            }
        }
        return palabrasFiltradas;
    }
}
