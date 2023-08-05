package com.test.views.helloworld;

import com.test.views.MainLayout;
import com.vaadin.flow.component.Key;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.dependency.CssImport;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.router.RouteAlias;
import com.wontlost.ckeditor.Constants;
import com.wontlost.ckeditor.VaadinCKEditor;
import com.wontlost.ckeditor.VaadinCKEditorBuilder;

@PageTitle("Hello World")
@Route(value = "hello", layout = MainLayout.class)
@RouteAlias(value = "", layout = MainLayout.class)
@CssImport("./views/main/main-view.css")
public class HelloWorldView extends VerticalLayout
{
    public HelloWorldView()
    {
        VaadinCKEditor classicEditor = new VaadinCKEditorBuilder().with(builder -> {
            builder.editorData = TEST_VALUE;
            builder.editorType = Constants.EditorType.CLASSIC;
            builder.theme = Constants.ThemeType.LIGHT;
        }).createVaadinCKEditor();

        Button resetButton = new Button("Reset");
        resetButton.addClickListener(e -> classicEditor.setValue(TEST_VALUE));
        resetButton.addClickShortcut(Key.ENTER);

        classicEditor.setWidthFull();
        classicEditor.setHeight("250px");

        add(classicEditor, resetButton);
    }

    private static final String TEST_VALUE = """
                <figure class="table">
                    <table>
                        <tbody>
                            <tr>
                                <td>
                                    hello
                                </td>
                                <td>
                                    world
                                </td>
                                <td>
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
                                <td>
                                    last
                                </td>
                                <td>
                                    row
                                </td>
                                <td>
                                    here
                                </td>
                            </tr>
                        </tbody>
                    </table>
                </figure>                
            """;
}
