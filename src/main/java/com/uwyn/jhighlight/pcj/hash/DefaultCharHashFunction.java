/*
 *  Primitive Collections for Java.
 *  Copyright (C) 2002, 2003  Søren Bak
 *
 *  This library is free software; you can redistribute it and/or
 *  modify it under the terms of the GNU Lesser General Public
 *  License as published by the Free Software Foundation; either
 *  version 2.1 of the License, or (at your option) any later version.
 *
 *  This library is distributed in the hope that it will be useful,
 *  but WITHOUT ANY WARRANTY; without even the implied warranty of
 *  MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the GNU
 *  Lesser General Public License for more details.
 *
 *  You should have received a copy of the GNU Lesser General Public
 *  License along with this library; if not, write to the Free Software
 *  Foundation, Inc., 59 Temple Place, Suite 330, Boston, MA  02111-1307  USA
 */
package com.uwyn.jhighlight.pcj.hash;

import java.io.Serializable;

/**
 * This class provides a default hash function for
 * char values.
 *
 * @author S&oslash;ren Bak
 * @version 1.2     2003/5/3
 * @serial exclude
 * @since 1.0
 */
public class DefaultCharHashFunction implements CharHashFunction, Serializable
{

	/**
	 * Default instance of this hash function.
	 */
	public static final CharHashFunction INSTANCE = new DefaultCharHashFunction();

	/**
	 * Default constructor to be invoked by sub-classes.
	 */
	protected DefaultCharHashFunction()
	{
	}

	public int hash(char v)
	{
		return (int) v;
	}

}
