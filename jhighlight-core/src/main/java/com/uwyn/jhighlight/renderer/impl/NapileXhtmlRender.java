/*
 * Copyright 2010-2012 napile.org
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *        http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.uwyn.jhighlight.renderer.impl;

import java.util.HashMap;
import java.util.Map;

import com.uwyn.jhighlight.highlighter.ExplicitStateHighlighter;
import com.uwyn.jhighlight.highlighter.NapileHighlighter;
import com.uwyn.jhighlight.renderer.XhtmlRenderer;

/**
 * @author VISTALL
 * @date 13:53/28.06.2012
 */
public class NapileXhtmlRender extends XhtmlRenderer
{
	public final static Map<String, String> DEFAULT_CSS = new HashMap<String, String>()
	{{
			put("h1", "font-family: sans-serif; " +
					"font-size: 16pt; " +
					"font-weight: bold; " +
					"color: rgb(0,0,0); " +
					"background: rgb(210,210,210); " +
					"border: solid 1px black; " +
					"padding: 5px; " +
					"text-align: center;");

			put("code", "color: rgb(0,0,0); " +
					"font-family: monospace; " +
					"font-size: 12px; " +
					"white-space: nowrap;");

			put(".napile_plain", "color: rgb(0,0,0);");

			put(".napile_string", "color: rgb(0,200,0);");

			put(".napile_keyword", "color: rgb(0,0,0); " + "font-weight: bold;");

			put(".napile_type", "color: rgb(0,44,221);");

			put(".napile_operator", "color: rgb(0,124,31);");

			put(".napile_separator", "color: rgb(0,33,255);");

			put(".napile_literal", "color: rgb(188,0,0);");

			put(".napile_comment", "color: rgb(147,147,147); " + "background-color: rgb(247,247,247);");

			put(".napile_javadoc_comment", "color: rgb(147,147,147); " +
					"background-color: rgb(247,247,247); " +
					"font-style: italic;");

			put(".napile_javadoc_tag", "color: rgb(147,147,147); " +
					"background-color: rgb(247,247,247); " +
					"font-style: italic; " +
					"font-weight: bold;");
		}};

	@Override
	protected Map<String, String> getDefaultCssStyles()
	{
		return DEFAULT_CSS;
	}

	@Override
	protected String getCssClass(int style)
	{
		switch(style)
		{
			case NapileHighlighter.PLAIN_STYLE:
				return "napile_plain";
			case NapileHighlighter.KEYWORD_STYLE:
				return "napile_keyword";
			case NapileHighlighter.OPERATOR_STYLE:
				return "napile_operator";
			case NapileHighlighter.SEPARATOR_STYLE:
				return "napile_separator";
			case NapileHighlighter.LITERAL_STYLE:
				return "napile_literal";
			case NapileHighlighter.JAVA_COMMENT_STYLE:
				return "napile_comment";
			case NapileHighlighter.JAVADOC_COMMENT_STYLE:
				return "napile_javadoc_comment";
			case NapileHighlighter.JAVADOC_TAG_STYLE:
				return "napile_javadoc_tag";
			case NapileHighlighter.STRING_STYLE:
				return "napile_string";
		}

		return null;
	}

	@Override
	protected ExplicitStateHighlighter getHighlighter()
	{
		return new NapileHighlighter();
	}
}
