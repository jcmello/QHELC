package py.com.pdi;

import java.io.File;

import ij.IJ;
import ij.ImagePlus;
import ij.process.ImageProcessor;

public class TestQHELC {

	public static void main(String[] args) {
		String sCarpAct = System.getProperty("user.dir").concat("\\imagenes");
		File carpeta = new File(sCarpAct);
		String[] listado = carpeta.list();

		if (listado == null || listado.length == 0) {
			System.out.println("No hay elementos dentro de la carpeta actual");
			return;
		} else {
			for (int i = 0; i < listado.length; i++) {
				System.out.println(listado[i]);
				String ruta = sCarpAct.concat("\\").concat(listado[i]);
				System.out.println(ruta);
				ImagePlus im = IJ.openImage(ruta); // carga la imagen
				im.show(); // Muestra la imagen original

				ImagePlus im2 = im.duplicate();

				// Inicio del algoritmo
				long time_start = System.currentTimeMillis();

				// Agregar el algoritmo de QHELC
				QHELC qhelc = new QHELC();
				ImageProcessor ip = im2.getProcessor();
				qhelc.run(ip);

				// Fin del tiempo en milisegundos
				long time_end = System.currentTimeMillis();
				long time = time_end - time_start;

				im2.show();

				// Guardar los resultados
				String rGuardar = System.getProperty("user.dir").concat("\\resultados\\QHELC\\").concat(listado[i]);
				System.out.println(rGuardar);
				IJ.saveAs(im2, "tif", rGuardar);
			}
		}
	}
}
