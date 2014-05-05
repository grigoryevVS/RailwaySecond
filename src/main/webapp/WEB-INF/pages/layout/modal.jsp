<!-- Modal -->
<div id="myModal" class="modal hide fade" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
    <div class="modal-header">
        <h3 id="myModalLabel">Confirm</h3>
    </div>
    <div class="modal-body">
        <p>Delete target object?</p>
    </div>
    <div class="modal-footer">
        <button id="cls" class="btn" data-dismiss="modal" aria-hidden="true">Cancel</button>
        <button id="ok" class="btn btn-success">Ok</button>
    </div>
</div>

<script type="text/javascript">
    var urlQuery;
    $(document).ready(function () {
        $('#cls').click(function () {
            $('#myModal').modal('hide');
        });
    });
    $(document).ready(function () {
        $('#ok').click(function () {
            $('#myModal').modal('hide');
            document.location.href = urlQuery;
        });
    });
    function getModal(url) {
        urlQuery = url
        $('#myModal').modal('show');
    }
</script>

