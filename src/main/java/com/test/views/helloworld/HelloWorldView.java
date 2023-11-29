package com.test.views.helloworld;

import com.test.views.MainLayout;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.dependency.CssImport;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.router.RouteAlias;
import com.wontlost.ckeditor.Constants;
import com.wontlost.ckeditor.VaadinCKEditor;
import com.wontlost.ckeditor.VaadinCKEditorBuilder;
import org.apache.commons.io.IOUtils;

import java.io.FileInputStream;
import java.nio.charset.StandardCharsets;

@PageTitle("Hello World")
@Route(value = "hello", layout = MainLayout.class)
@RouteAlias(value = "", layout = MainLayout.class)
@CssImport("./views/main/main-view.css")
public class HelloWorldView extends VerticalLayout
{
    public HelloWorldView()
    {
        String testReport = getTestReportFromFile();

        VaadinCKEditor classicEditor = new VaadinCKEditorBuilder().with(builder -> {
            builder.editorData = testReport;
            builder.editorType = Constants.EditorType.CLASSIC;
            builder.theme = Constants.ThemeType.LIGHT;
        }).createVaadinCKEditor();

        Button resetButton = new Button("Reset", click -> classicEditor.setValue(TEST_VALUE));
        Button reportButton = new Button("Report", click -> classicEditor.setValue(testReport));

        programmaticallyAddCssStyles(classicEditor);

        classicEditor.setWidthFull();
        classicEditor.setHeight("800px");

        add(classicEditor, resetButton, reportButton);
    }

    private static String getTestReportFromFile() {
        try {
            return IOUtils.toString(new FileInputStream("src/main/resources/exampleReport.html"), StandardCharsets.UTF_8);
        } catch (Exception e) {
            e.printStackTrace();
            return "error";
        }
    }

    private static void programmaticallyAddCssStyles(VaadinCKEditor classicEditor)
    {
//        classicEditor.getElement().executeJs("var style = document.createElement('style');" +
//				"style.type = 'text/css';" +
//				"style.appendChild(document.createTextNode('body .ck-content .table table td, .ck-content .table table th { border: 1px solid #fff; }'));" +
//				"document.head.appendChild(style);");
    }

    private static final String TEST_VALUE = """
                <figure class="table">
                    <table>
                        <tbody>
                            <tr>
                                <td style="background-color:#ccc;">
                                    hello
                                </td>
                                <td style="background-color:#ccc;">
                                    world
                                </td>
                                <td style="background-color:#ccc;">
                                    again
                                </td>
                            </tr>
                            <tr>
                                <td>
                                    second
                                </td>
                                <td>
                                    row
                                </td>
                                <td>
                                    here
                                </td>
                            </tr>
                            <tr>
                                <td style="border-top:1px solid red;">
                                    last
                                </td>
                                <td style="border-top:1px solid red;">
                                    row
                                </td>
                                <td style="border-top:2px solid red;">
                                    here
                                </td>
                            </tr>
                        </tbody>
                    </table>
                </figure>                
            """;
}
