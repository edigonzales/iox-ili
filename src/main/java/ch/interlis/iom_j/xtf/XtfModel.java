/* This file is part of the iox-ili project.
 * For more information, please see <http://www.eisenhutinformatik.ch/iox-ili/>.
 *
 * Copyright (c) 2006 Eisenhut Informatik AG
 * Permission is hereby granted, free of charge, to any person obtaining a 
 * copy of this software and associated documentation files (the "Software"), 
 * to deal in the Software without restriction, including without limitation 
 * the rights to use, copy, modify, merge, publish, distribute, sublicense,
 * and/or sell copies of the Software, and to permit persons to whom the 
 * Software is furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included 
 * in all copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS
 * OR IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL 
 * THE AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER 
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING 
 * FROM, OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER 
 * DEALINGS IN THE SOFTWARE.
 */
package ch.interlis.iom_j.xtf;

/** Represents a MODEL entry in the HEADERSECTION of an XTF file.
 * @author ceis
 *
 */
public class XtfModel {
	private String name=null;
	private String uri=null;
	private String version=null;
	public XtfModel(){
		
	}
	/** Creates a new instance with the given name, uri and version.
	 * @param name Name of model
	 * @param uri URI of issuer of model
	 * @param version version of model
	 */
	public XtfModel(String name, String uri,String version){
		this.name=name;
		this.uri=uri;
		this.version=version;
	}
	/** Gets the name of the model.
	 */
	public String getName() {
		return name;
	}
	/** Sets the name of the model.
	 */
	public void setName(String name) {
		this.name = name;
	}
	/** Gets the uri of the issuer of the model.
	 */
	public String getUri() {
		return uri;
	}
	/** Sets the uri of the issuer of the model.
	 */
	public void setUri(String uri) {
		this.uri = uri;
	}
	/** Gets the version of the model.
	 */
	public String getVersion() {
		return version;
	}
	/** Sets the version of the model.
	 */
	public void setVersion(String version) {
		this.version = version;
	}
}
