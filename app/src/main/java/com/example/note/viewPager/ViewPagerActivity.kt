package com.example.note.viewPager

import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.viewpager2.widget.ViewPager2
import com.example.note.Constants
import com.example.note.R
import com.example.note.SaveDialogFragment
import com.example.note.data.Note
import com.example.note.data.NoteRepository


class ViewPagerActivity : AppCompatActivity(), SaveDialogFragment.SaveDialogListener {

    companion object {
        private const val DIALOG_TAG = "616"
    }

    private lateinit var viewModel: PagerViewModel

    private lateinit var pager: ViewPager2
    private lateinit var myAdapter: PagerAdapter
    private lateinit var dialog: SaveDialogFragment
    lateinit var currentFragment: NotePagerFragment

    private var isAdding: Boolean = false
    private var position: Int? = 1

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_view_pager)

        isAdding = intent.getBooleanExtra(Constants.Transaction.ADD_ACTION, false)

        viewModel =
            ViewModelProvider(this, PagerViewModelFactory(NoteRepository(applicationContext))).get(
                PagerViewModel::class.java
            )

        position = if (isAdding) viewModel.getList(isAdding).size else intent.getIntExtra(
            Constants.Transaction.PASS_ACTION,
            0
        )

        myAdapter = PagerAdapter(this, viewModel.getList(isAdding))
        pager = findViewById(R.id.pager)
        pager.apply {
            adapter = myAdapter
            currentItem = if (isAdding) viewModel.getList(isAdding).size else position!!
        }
        subscribeToViewModel()
    }

    private fun subscribeToViewModel() {

        viewModel.onEmptyNote.observe(this) {
            Toast.makeText(
                applicationContext,
                getString(R.string.toast_emptyMessage),
                Toast.LENGTH_SHORT
            ).show()
        }

        viewModel.onSaveSuccess.observe(this) {
            Toast.makeText(
                applicationContext,
                getString(R.string.toast_savedMessage),
                Toast.LENGTH_SHORT
            ).show()
            broadcastNote(viewModel.broadcastNote.value)
        }

        viewModel.onShareNote.observe(this) {
            shareNote()
        }

        viewModel.onSavedBtnPressed.observe(this) {
            onSavedBtnPressed()
        }
    }


    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu, menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        val header = currentFragment.binding?.noteNameEditText?.text.toString()
        val content = currentFragment.binding?.noteTextEditText?.text.toString()

        return when (item.itemId) {
            R.id.menu_save -> {
                viewModel.tryToSaveOrUpdate(header, content)
                true
            }
            R.id.menu_share -> {
                viewModel.tryToShare(header, content)
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }

    private fun shareNote() {
        val header = currentFragment.binding?.noteNameEditText?.text.toString()
        val content = currentFragment.binding?.noteTextEditText?.text.toString()

        startActivity(Intent(Intent.ACTION_SEND).apply {
            type = Constants.DetailsView.INTENT_TEXT_TYPE
            putExtra(Intent.EXTRA_TEXT, "$header\n$content")
        })
    }

    private fun broadcastNote(note: Note?) {
        sendBroadcast(Intent().apply {
            action = "com.skbsc.broadcastnote"
            flags = Intent.FLAG_INCLUDE_STOPPED_PACKAGES
            putExtra(Constants.Broadcast.TAG_HEADER, note?.header)
            putExtra(Constants.Broadcast.TAG_CONTENT, note?.content)
        })
    }

    private fun onSavedBtnPressed() {
        dialog = SaveDialogFragment()
        dialog.show(supportFragmentManager, DIALOG_TAG)
    }


    override fun onDialogPositiveClick() {
        viewModel.handleDialogPositiveClick(
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