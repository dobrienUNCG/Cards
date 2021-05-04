/**MIT License

Copyright (c) 2021 Devin M. O'Brien

Permission is hereby granted, free of charge, to any person obtaining a copy
of this software and associated documentation files (the "Software"), to deal
in the Software without restriction, including without limitation the rights
to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
copies of the Software, and to permit persons to whom the Software is
furnished to do so, subject to the following conditions:

The above copyright notice and this permission notice shall be included in all
copies or substantial portions of the Software.

THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
SOFTWARE.
**/
package Cards.data.request;

/**
 * Date: 05/04/21
 * Enumerated request types.
 * @author Devin M. O'Brien
 */
public enum RequestType {
    GET_EVENTID, GET_INFO_ALL, GET_IS_ALL_DAY, GET_DUE_DATE, GET_DESCRIPTION,
    GET_DATE_CREATED, GET_DUE_TODAY, GET_UPCOMING, GET_LIMIT,
    POST_SUMMARY, POST_DATE_BEGIN, POST_DATE_END, POST_DESCRIPTION, POST_LIMIT,
    DELETE_EVENT,
    PUT_CALENDAR, PUT_EVENT, PUT;
    int i;

    RequestType() {

    }
    public void setI(int _i){
        this.i = _i;
    }

    RequestType(int _value) {
        this.i = _value;
    }

    //=================  GETTERS ===============
    public int getI() {
        return i;
    }

    public boolean isDelete() {
        return this.ordinal() == DELETE_EVENT.ordinal();
    }

    public boolean isGet() {
        return this.ordinal() < GET_LIMIT.ordinal();
    }

    public boolean isPost() {
        return this.ordinal() > GET_LIMIT.ordinal() && this.ordinal() < POST_LIMIT.ordinal();
    }

    public boolean isPut() {
        return this.ordinal() > DELETE_EVENT.ordinal();
    }


}
