function fnFormatDetails ( oTable, nTr )
{
    var aData = oTable.fnGetData( nTr );
    var sOut = '<table width="100%" cellpadding="5" cellspacing="0" border="0" style="padding-left:50px;">';
    sOut += '<tr><td colspan="2" rowspan="3"><div class="profile-pic text-center"><img class="image-profil" alt="" src="'+aData[9]+'"></div></td><td>Telephone : </td><td>'+aData[3]+'</td></tr>';
    sOut += '<tr><td>Date de naissance : </td><td>'+aData[8]+'</td></tr>';
    sOut += '<tr><td>Sexe : </td><td>'+aData[4]+'</td></tr>';
    sOut += '<tr><td>Nom &amp; Prènom : </td><td>'+aData[1]+'</td><td>Adresse : </td><td>'+aData[7]+'</td></tr>';
    sOut += '<tr><td>Email : </td><td>'+aData[2]+'</td><td>Profil : </td><td>'+aData[5]+'</td></tr>';
    if(aData[5]=="Evaluateur")
         sOut += '<div class="btn-group"><a href="#addUserModal" data-toggle="modal"><button class="btn btn-default" data-toggle="button"><i class="fa fa-calendar-o"> Consulter BAP</i></button></a></div>';
    sOut += '</table>';

    return sOut;
}

$(document).ready(function() {

    $('#dynamic-table').dataTable( {
        "aaSorting": [[ 1, "desc" ]]
    } );

    /*
     * Insert a 'details' column to the table
     */
    var nCloneTh = document.createElement( 'th' );
    var nCloneTd = document.createElement( 'td' );
    nCloneTd.innerHTML = '<img src="images/details_open.png">';
    nCloneTd.className = "center";

    $('#hidden-table-info thead tr').each( function () {
        this.insertBefore( nCloneTh, this.childNodes[0] );
    } );

    $('#hidden-table-info tbody tr').each( function () {
        this.insertBefore(  nCloneTd.cloneNode( true ), this.childNodes[0] );
    } );

    /*
     * Initialse DataTables, with no sorting on the 'details' column
     */
    var oTable = $('#hidden-table-info').dataTable( {
        "aoColumnDefs": [
            { "bSortable": false, "aTargets": [ 0 ] }
        ],
        "aaSorting": [[1, 'asc']]
    });

    /* Add event listener for opening and closing details
     * Note that the indicator for showing which row is open is not controlled by DataTables,
     * rather it is done here
     */
    $(document).on('click','#hidden-table-info tbody td img',function () {
        var nTr = $(this).parents('tr')[0];
        if ( oTable.fnIsOpen(nTr) )
        {
            /* This row is already open - close it */
            this.src = "images/details_open.png";
            oTable.fnClose( nTr );
        }
        else
        {
            /* Open this row */
            this.src = "images/details_close.png";
            oTable.fnOpen( nTr, fnFormatDetails(oTable, nTr), 'details' );
        }
    } );
} );