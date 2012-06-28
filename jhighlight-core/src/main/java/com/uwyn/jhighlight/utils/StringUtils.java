/*
 * Copyright 2001-2006 Geert Bevin <gbevin[remove] at uwyn dot com>
 * Distributed under the terms of either:
 * - the common development and distribution license (CDDL), v1.0; or
 * - the GNU Lesser General Public License, v2.1 or later
 * $Id: StringUtils.java 3108 2006-03-13 18:03:00Z gbevin $
 */
package com.uwyn.jhighlight.utils;

import java.util.regex.Pattern;

/**
 * General purpose class containing common <code>String</code> manipulation
 * methods.
 *
 * @author Geert Bevin (gbevin[remove] at uwyn dot com)
 * @version $Revision: 3108 $
 * @since 1.0
 */
public abstract class StringUtils
{

	private StringUtils()
	{
	}



	/**
	 * Checks if the name filters through a series of including and excluding
	 * regular expressions.
	 *
	 * @param name     The <code>String</code> that will be filtered.
	 * @param included An array of regular expressions that need to succeed
	 * @param excluded An array of regular expressions that need to fail
	 * @return <code>true</code> if the name filtered through correctly; or
	 *         <p/>
	 *         <code>false</code> otherwise.
	 * @since 1.0
	 */
	public static boolean filter(String name, Pattern[] included, Pattern[] excluded)
	{
		if(null == name)
		{
			return false;
		}

		boolean accepted = false;

		// retain only the includes
		if(null == included)
		{
			accepted = true;
		}
		else
		{
			Pattern pattern;
			for(int i = 0; i < included.length; i++)
			{
				pattern = included[i];

				if(pattern != null && pattern.matcher(name).matches())
				{
					accepted = true;
					break;
				}
			}
		}

		// remove the excludes
		if(accepted && excluded != null)
		{
			Pattern pattern;
			for(int i = 0; i < excluded.length; i++)
			{
				pattern = excluded[i];

				if(pattern != null && pattern.matcher(name).matches())
				{
					accepted = false;
					break;
				}
			}
		}

		return accepted;
	}

	/**
	 * Converts all tabs on a line to spaces according to the provided tab
	 * width.
	 *
	 * @param line     The line whose tabs have to be converted.
	 * @param tabWidth The tab width.
	 * @return A new <code>String</code> object containing the line with the
	 *         replaced tabs.
	 * @since 1.0
	 */
	public static String convertTabsToSpaces(String line, int tabWidth)
	{
		StringBuilder result = new StringBuilder();

		int tab_index = -1;
		int last_tab_index = 0;
		int added_chars = 0;
		int tab_size;
		while((tab_index = line.indexOf("\t", last_tab_index)) != -1)
		{
			tab_size = tabWidth - ((tab_index + added_chars) % tabWidth);
			if(0 == tab_size)
			{
				tab_size = tabWidth;
			}
			added_chars += tab_size - 1;
			result.append(line.substring(last_tab_index, tab_index));
			result.append(org.apache.commons.lang3.StringUtils.repeat(" ", tab_size));
			last_tab_index = tab_index + 1;
		}
		if(0 == last_tab_index)
		{
			return line;
		}
		else
		{
			result.append(line.substring(last_tab_index));
		}

		return result.toString();
	}
}


