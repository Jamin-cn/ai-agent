package com.jamin.aiagent.tools;

import org.springframework.ai.support.ToolCallbacks;
import org.springframework.ai.tool.ToolCallback;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * 集中的工具注册类
 */
@Configuration
public class ToolRegistration {

    private String searchApiKey;

    @Bean
    public ToolCallback[] allTools() {
        FileOoperationTool fileOperationTool = new FileOoperationTool();
        ResourceDownloadTool resourceDownloadTool = new ResourceDownloadTool();
        WebSearchTool webSearchTool = new WebSearchTool(searchApiKey);
        WebScrapingTool webScrapingTool = new WebScrapingTool();
        TerminalOperationTool terminalOperationTool = new TerminalOperationTool();
        TerminateTool terminateTool = new TerminateTool();
        
        return ToolCallbacks.from(
                fileOperationTool,
                resourceDownloadTool,
                webSearchTool,
                webScrapingTool,
                terminalOperationTool,
                terminateTool
        );
    }
}