package com.example.note.viewPager

import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.viewpager2.widget.ViewPager2
import com.example.note.Constants
import com.example.note.R
import com.example.note.SaveDialogFragment
import com.example.note.data.NoteRepository


class ViewPagerActivity : AppCompatActivity(), ViewPagerActivityView, SaveDialogFragment.SaveDialogListener{

    companion object{
        private const val DIALOG_TAG = "616"
    }

    private lateinit var presenter: ViewPagerActivityPresenter
    private lateinit var pager: ViewPager2
    private lateinit var adapter: PagerAdapter
    private lateinit var dialog: SaveDialogFragment
    lateinit var currentFragment: NotePagerFragment

    private var isAdding: Boolean = false
    private var position: Int = 1

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_view_pager)

        position = intent.getIntExtra(Constants.Transaction.PASS_ACTION,0)
        isAdding = intent.getBooleanExtra(Constants.Transaction.OPEN_ACTION, false)

        presenter = ViewPagerActivityPresenter(
            this,
            NoteRepository(applicationContext),
            intent.getBooleanExtra(Constants.Transaction.OPEN_ACTION, false)
        )

        pager = findViewById(R.id.pager)
        adapter = PagerAdapter(this, presenter.getList())

        pager.adapter = adapter
        pager.currentItem = if(isAdding) presenter.getList().size else position
    }


    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu, menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        val header = currentFragment.binding?.noteNameEditText?.text.toString()
        val content = currentFragment.binding?.noteTextEditText?.text.toString()

        return when(item.itemId){
            R.id.menu_save -> {
                presenter.tryToSaveOrUpdate(header,content)
                true
            }
            R.id.menu_share -> {
                presenter.tryToShare(header,content)
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }

    override fun shareNote(header: String, content: String) {
        startActivity(Intent(Intent.ACTION_SEND).apply {
            type = Constants.DetailsView.INTENT_TEXT_TYPE
            putExtra(Intent.EXTRA_TEXT, "$header\n$content")
        })
    }

    override fun onSavedBtnPressed() {
        dialog = SaveDialogFragment()
        dialog.show(supportFragmentManager, DIALOG_TAG)
    }

    override fun onEmptyNote() {
        Toast.makeText(applicationContext,getString(R.string.toast_emptyMessage),Toast.LENGTH_SHORT).show()
    }

    override fun onDialogPositiveClick() {
        presenter.handleDialogPositiveClick(
            pager.currentItem,
            isAdding,
            currentFragment.id!!,
            currentFragment.binding?.noteNameEditText?.text.toString(),
            currentFragment.binding?.noteTextEditText?.text.toString(),
            getString(R.string.note_sample_time)
        )
        dialog.dismiss()
    }

    override fun onDialogNegativeClick() {
        dialog.dismiss()
    }
}