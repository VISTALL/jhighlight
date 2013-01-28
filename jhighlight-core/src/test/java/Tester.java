/*
 * Copyright 2010-2013 napile.org
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

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

import org.apache.commons.io.FileUtils;
import org.junit.Assert;
import com.uwyn.jhighlight.renderer.Renderer;
import com.uwyn.jhighlight.renderer.XhtmlRendererFactory;

/**
 * @author VISTALL
 * @date 16:03/28.01.13
 */
public class Tester extends Assert
{
	public void test(String path)
	{
		File file = new File("jhighlight-core/src/testdata/" + path);
		if(!file.exists())
		{
			throw new IllegalArgumentException("File is not found " + path);
		}

		Renderer renderer = XhtmlRendererFactory.getRenderer(com.uwyn.jhighlight.utils.FileUtils.getExtension(file.getName()));
		try
		{
			ByteArrayOutputStream arrayOutputStream = new ByteArrayOutputStream();
			String result = "";
			File resultFile = new File(file + ".html");
			if(resultFile.exists())
			{
				result = FileUtils.readFileToString(resultFile);
			}

			renderer.highlight(file.getName(), new FileInputStream(file), arrayOutputStream, "UTF-8", false);

			assertEquals(arrayOutputStream.toString(), result);
		}
		catch(IOException e)
		{
			throw new IllegalArgumentException(e);
		}
	}
}
