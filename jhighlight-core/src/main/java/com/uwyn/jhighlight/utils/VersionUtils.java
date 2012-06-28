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

package com.uwyn.jhighlight.utils;

import java.io.File;
import java.util.jar.Attributes;
import java.util.jar.JarFile;

import com.uwyn.jhighlight.JHighlight;

/**
 * @author VISTALL
 * @date 13:58/28.06.2012
 */
public class VersionUtils
{
	public static final String UNDEFINED = "UNDEFINED";
	public static final String URL = "http://napile.org";

	private VersionUtils()
	{}

	public static String getVersion()
	{
		String version = UNDEFINED;
		try
		{
			File jarName = Locator.getClassSource(JHighlight.class);
			JarFile jarFile = new JarFile(jarName);

			Attributes attrs = jarFile.getManifest().getMainAttributes();

			version = attrs.getValue("Implementation-Version");
		}
		catch(Throwable e)
		{}

		return version;
	}
}
