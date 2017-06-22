<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!-- 报表设置 -->
<div class="modal fade tbset" tabindex="-1" role="dialog" aria-labelledby="mySmallModalLabel" data-backdrop="static">
    <div class="modal-dialog xinzm">
        <div class="modal-content">
            <div class="xzmain">
                <h3>查询设置</h3>
                    <div class="settb">
                    <form id="fieldform">
                        <table class="table table-bordered " id="tableqryfield">
                            <thead >
                            <tr>
                                <th class="onea">
                                    <label>
                                   <span>
                                       <input type="checkbox" onclick="choiseAll(this)" id="idchkall"/>
                                   </span>
                                        	全选
                                    </label>
                                </th>
                                <th>参数项</th>
                            </tr>
                            </thead>
                            <tbody id="trqryfield"></tbody>                           
                        </table>
                     </form>
                    </div>

                    <div class="modal-footer">
                        <p>
                            <button type="button" class="save"  onclick="setField()">确认</button>
                            <button type="button" class="closebtn" data-dismiss="modal">取消</button>
                        </p>
                    </div>
            </div>

        </div>
    </div>
</div>