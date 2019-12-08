package profesor;
//Servlet que generará los ejemplos a través de Fabric

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.jdom.Element;
import procesos.lectorA;
import procesos.lectorCE;

public class modificarCanvas extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        HttpSession session = request.getSession();

        String userName = (String) session.getAttribute("username");
        String grupo = (String) session.getAttribute("grupo");
        String realpath = (String) session.getAttribute("elcaminoreal");
        String ejercicio = request.getParameter("ejercicio");
        session.setAttribute("ejercicio", ejercicio);
        String id = (String) session.getAttribute("id");
        lectorA archivoXML = new lectorA(realpath + "archivos.xml");
        lectorCE archivoXML2 = new lectorCE(realpath + "ejercicioCanvas.xml");
        //Podrá ser que el canvas ya exista y sólo deseé modificarlo?...
        String can = archivoXML2.getCE(ejercicio);
        if (userName != null && id != null) {
            PrintWriter out = response.getWriter();
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Profesor</title>");
            //Scripts necesarios para que funcione el canvas
            out.println("<script type='text/javascript' src='js/fabric.min.js'></script>");
            out.println("<script type='text/javascript' src='js/jquery.min.js'></script>");
            out.println("<script type='text/javascript' src='js/responsivevoice.js'></script>");
            out.println("<script src='js/bootstrap.min.js'></script>");
            out.println("<link rel='stylesheet' href='css/bootstrap.min.css'>");
            out.println("<link rel='stylesheet' href='css/canvas.css'>");
            out.println("</head>");
            out.println("<body>");
            out.println("<div id='images'>");
            for (Element e : archivoXML.getArchivos()) {
                if (e.getAttributeValue("idprofe").equals(id)) {
                    if (e.getText().contains(".jpg") || e.getText().contains(".png") || e.getText().contains(".gif") || e.getText().contains(".jpeg")) {
                        out.println("<img draggable=\"true\" src='files/" + e.getText() + "' width=\"100\" height=\"100\"></img>");

                    }
                }
            }
            out.println("</div>");
            out.println("<div align='center' id='canvas-container'>");
            out.println("<canvas id='c' width=\"800\" height=\"800\" style=\"border:1px solid #000000;\"></canvas>");
            out.println("<script>");
            //script de canvas
            out.println("   var imageLoader = document.getElementById('imageLoader');\n"
                    + "        //Declaramos las imagenes para el canvas\n"
                    + "    var canvas = new fabric.Canvas('c');\n");
            if (!"".equals(can)) {//Si el canvas ya existía sólo se carga desde el xml
                out.println("canvas.loadFromJSON(" + can + ");");
            }
            out.println("function handleDragStart(e) {\n"
                    + "    		[].forEach.call(images, function (img) {\n"
                    + "        	img.classList.remove('img_dragging');\n"
                    + "    		});\n"
                    + "    		this.classList.add('img_dragging');\n"
                    + "		}\n"
                    + "\n"
                    + "	function handleDragOver(e) {\n"
                    + "    if (e.preventDefault) {\n"
                    + "        e.preventDefault(); // Permite drop\n"
                    + "    }\n"
                    + "    e.dataTransfer.dropEffect = 'copy'; \n"
                    + "    return false;\n"
                    + "	}\n"
                    + "\n"
                    + "function handleDragEnter(e) {\n"
                    + "    // Elemento que entra al cabvas\n"
                    + "    this.classList.add('over');\n"
                    + "}\n"
                    + "\n"
                    + "function handleDragLeave(e) {\n"
                    + "    this.classList.remove('over'); // Elemento anterior\n"
                    + "}\n"
                    + "\n"
                    + "function handleDrop(e) {\n"
                    + "    // Elemento actual\n"
                    + "    if (e.stopPropagation) {\n"
                    + "        e.stopPropagation(); // Detiene redireccionamientos\n"
                    + "    }\n"
                    + "    //Imagenes Drag And Dróp\n"
                    + "    var img = document.querySelector('#images img.img_dragging ');\n"
                    + "    console.log('event: ', e);\n"
                    + "    var newImage = new fabric.Image(img,{\n"
                    + "        width: 100,\n"
                    + "        height: 100,\n"
                    + "        left: e.layerX,\n"
                    + "        top: e.layerY\n"
                    + "        \n"
                    + "    });\n"
                    + "    var widthO = img.naturalWidth;\n"
                    + "    var heightO = img.naturalHeight;\n"
                    + "    var sx = widthO/833.3333;\n"
                    + "        newImage.set({\n"
                    + "            width: widthO,\n"
                    + "            height: heightO,\n"
                    + "            scaleX: .23,\n"
                    + "            scaleY: .23,\n"
                    + "            left: e.layerX,\n"
                    + "            top: e.layerY,\n"
                    + "          });\n"
                    + "        canvas.add(newImage);\n"
                    + "        return false;\n"
                    + "    }\n"
                    + "    function handleDragEnd(e) {\n"
                    + "        // Deja el objeto donde el cursor\n"
                    + "        [].forEach.call(images, function (img) {\n"
                    + "            img.classList.remove('img_dragging');\n"
                    + "        });\n"
                    + "    }\n"
                    + "    //Removedor de objetos\n"
                    + "    var bin;\n"
                    + "    var selectedObject;\n"
                    + "    fabric.Image.fromURL('img/descarga.png', function(img2) {\n"
                    + "    img2.set({\n"
                    + "        width: 200,\n"
                    + "        height: 200,\n"
                    + "        left: 720,\n"
                    + "        top: 720,\n"
                    + "        selectable: false\n"
                    + "      });\n"
                    + "      bin = img2;\n"
                    + "      canvas.add(img2);\n"
                    + "    });\n"
                    + "    canvas.on('object:selected', function(evn) {\n"
                    + "      selectedObject = evn.target;\n"
                    + "    })\n"
                    + "    canvas.on('mouse:up', function(evn) {\n"
                    + "      var x = evn.e.offsetX;\n"
                    + "      var y = evn.e.offsetY;\n"
                    + "      if (x > bin.left && x < (bin.left + bin.width) && y > bin.top && y < (bin.top + bin.height)||(x < bin.left && x > (bin.left + bin.width))&& (y < bin.top && y > (bin.top + bin.height))) {\n"
                    + "        canvas.remove(selectedObject);\n"
                    + "      }\n"
                    + "    });\n"
                    + "   	\n"
                    + "\n"
                    + "canvas.uniScaleTransform = true;\n"
                    + "\n"
                    + "var appObject = function() {\n"
                    + "\n"
                    + "  return {\n"
                    + "    __canvas: canvas,\n"
                    + "    __tmpgroup: {},\n"
                    + "\n"
                    + "    addText: function() {\n"
                    + "      var newID = (new Date()).getTime().toString().substr(5);\n"
                    + "      var text = new fabric.IText('ejemplo', {\n"
                    + "        fontFamily: 'arial black',\n"
                    + "        left: 500,\n"
                    + "        top: 500,\n"
                    + "        myid: newID,\n"
                    + "        objecttype: 'text'\n"
                    + "      });\n"
                    + "\n"
                    + "      this.__canvas.add(text);\n"
                    + "      this.addLayer(newID, 'text');\n"
                    + "    },\n"
                    + "    setTextParam: function(param, value) {\n"
                    + "      var obj = this.__canvas.getActiveObject();\n"
                    + "      if (obj) {\n"
                    + "        if (param == 'color') {\n"
                    + "          obj.setColor(value);\n"
                    + "        } else {\n"
                    + "          obj.set(param, value);\n"
                    + "        }\n"
                    + "        this.__canvas.renderAll();\n"
                    + "      }\n"
                    + "    },\n"
                    + "    setTextValue: function(value) {\n"
                    + "      var obj = this.__canvas.getActiveObject();\n"
                    + "      if (obj) {\n"
                    + "        obj.setText(value);\n"
                    + "        this.__canvas.renderAll();\n"
                    + "      }\n"
                    + "    },\n"
                    + "    addLayer: function() {\n"
                    + "\n"
                    + "    }\n"
                    + "\n"
                    + "  };\n"
                    + "}\n"
                    + "//ajax que cambia el texto seleccionado\n"
                    + "$(document).ready(function() {\n"
                    + "\n"
                    + "  var app = appObject();\n"
                    + "\n"
                    + "  $('.font-change').change(function(event) {\n"
                    + "    app.setTextParam($(this).data('type'), $(this).find('option:selected').val());\n"
                    + "  });\n"
                    + "\n"
                    + "  $('#add').click(function() {\n"
                    + "    app.addText();\n"
                    + "  });\n"
                    + "})\n"
                    + "\n"
                    + "\n"
                    + "	$(\"canvas\").click(function(e){\n"
                    + "			  var txt = canvas.getActiveObject().text;\n"
                    + "			  Decir(txt);\n"
                    + "			});\n"
                    + "\n"
                    + "			function Decir(say){\n"
                    + "				var voicelist = responsiveVoice.getVoices();\n"
                    + "				responsiveVoice.speak(say,\"Spanish Latin American Female\");\n"
                    + "			}\n"
                    + "\n"
                    + "    //Añadimos los eventos de drag and drop a las imagenes\n"
                    + "    var images = document.querySelectorAll('#images img');\n"
                    + "    [].forEach.call(images, function (img) {\n"
                    + "        img.addEventListener('dragstart', handleDragStart, false);\n"
                    + "        img.addEventListener('dragend', handleDragEnd, false);\n"
                    + "    });\n"
                    + "    // Añadimos el soporte de eventos al canvas\n"
                    + "    var canvasContainer = document.getElementById('canvas-container');\n"
                    + "    canvasContainer.addEventListener('dragenter', handleDragEnter, false);\n"
                    + "    canvasContainer.addEventListener('dragover', handleDragOver, false);\n"
                    + "    canvasContainer.addEventListener('dragleave', handleDragLeave, false);\n"
                    + "    canvasContainer.addEventListener('drop', handleDrop, false);");
            //Obtenemos un string mediante json, el cual mandamos como parametro al servlet
            //que subirá dicha cadena al xml, para que al momento de que el alumno lo recupere
            //se quede en la misma posición en donde la dejó
            out.println("function serializar() {\n"
                    + " var canvasstring = JSON.stringify(canvas);\n"
                    + "$.ajax({\n"
                    + "type: \"GET\",\n"
                    + "url:\"cambioCanvas\",\n"
                    + "data:{\"canv\":canvasstring},\n"
                    + "success: function () {\n" //con base al resultado le avisamos al usuario si su canvas se pudo subir o no
                    + "alert('Canvas guardado');\n"
                    + " }\n"
                    + ",error: function () {\n"
                    + "alert('canvas no guardado');\n"
                    + " }\n"
                    + "}); "
                    + "};");
            out.println("</script>");
            out.println ("<div class='col-xs-6 col-xs-offset-3 menuCanvas'>");
            out.println("<button id='add' class='btn btn-info'>Añadir</button>");
            out.println ("<select class=\"select2 font-change\" data-type=\"fontFamily\">"
                    + "  		<option value=\"Arial\">Arial</option>\n"
                    + "  		<option value=\"Arial Black\">Arial Black</option>\n"
                    + "  		<option value=\"Times New Roman\">Times New Roman</option>\n"
                    + "  		<option value=\"Helvetica\">Helvetica</option>\n"
                    + "  		<option value=\"Courier New\">Courier New</option>\n"
                    + "	</select>\n"
                    + "	<select class=\"select2 font-change\" data-type=\"fontSize\">\n"
                    + "	  <option value=\"10\">10</option>\n"
                    + "	  <option value=\"12\">12</option>\n"
                    + "	  <option value=\"14\">14</option>\n"
                    + "	  <option value=\"16\">16</option>\n"
                    + "	  <option value=\"18\">18</option>\n"
                    + "	  <option value=\"20\">20</option>\n"
                    + "	  <option value=\"24\">24</option>\n"
                    + "	  <option value=\"26\">26</option>\n"
                    + "	  <option value=\"28\">28</option>\n"
                    + "	  <option value=\"30\">30</option>\n"
                    + "	  <option value=\"32\">32</option>\n"
                    + "	  <option value=\"34\">34</option>\n"
                    + "	  <option value=\"36\">36</option>\n"
                    + "	  <option value=\"38\">38</option>\n"
                    + "	  <option value=\"40\">40</option>\n"
                    + "	</select>\n"
                    + "	<select class=\"select2 font-change\" data-type=\"color\">\n"
                    + "	  <option value=\"#000\">Negro</option>\n"
                    + "	  <option value=\"#00f\">Azul</option>\n"
                    + "	  <option value=\"#0f0\">Verde</option>\n"
                    + "	  <option value=\"#f00\">Rojo</option>\n"
                    + "	  <option value=\"#f0f\">Rosa</option>\n"
                    + "	  <option value=\"#ff7800\">Naranja</option>\n"
                    + "	  <option value=\"#822982\">Morado</option>\n"
                    + "	</select>");
            //Con este boton mandamos el canvas a json y este al servlet que lo guarda en xml
            out.println("<button class='btn btn-info' onClick='serializar()'>Serializar</button>");
            out.println("<a href='inicioprofe' class='btn btn-info'>Salir</a>");
            out.println("</div>");
            out.println("</div>");
            out.println("</div>");
            out.println("</body>");
            out.println("</html>");
        } else {
            response.sendRedirect("index.html");
        }

    }
}