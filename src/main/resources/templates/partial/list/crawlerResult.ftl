<div class="col-sm-7" style="margin-top: 15px">
    <form>
        <div class="input-group">
            <label for="crawl-result-select"></label>
            <select class="form-control" id="crawl-result-select">
                <option value="food">food</option>
            </select>
            <span class="input-group-btn">
                <button type="button" class="btn btn-primary"
                        onclick="loadResult()">查看结果</button>
            </span>
        </div>
    </form>
</div>
<div class="col-sm-12">
    <div class="panel panel-default basic-panel">
        <table class="table table-hover table-result" id="crawl-result-table">
            <thead>
            <tr></tr>
            </thead>
            <tbody>
            </tbody>
        </table>
    </div>
</div>
