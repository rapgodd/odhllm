package com.giyeon.odhllm.domain.dto;
import com.vladsch.flexmark.parser.Parser;
import com.vladsch.flexmark.util.ast.Node;
import com.vladsch.flexmark.html.HtmlRenderer;


import lombok.Data;

@Data
public class ResponseMessageDto {
    private String message;

    public ResponseMessageDto(String message) {
        this.message = message;
    }

    public void makeMoreReadable() {
        Parser parser = Parser.builder().build();
        Node document = parser.parse(this.message);
        HtmlRenderer renderer = HtmlRenderer.builder().build();
        this.message = renderer.render(document);
    }
}
