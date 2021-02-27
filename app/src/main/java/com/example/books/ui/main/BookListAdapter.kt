package com.example.books.ui.main

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.books.R
import com.example.books.data.BookResult
import com.example.books.databinding.BookGridItemBinding

class BookListAdapter (val context: Context,
                       val booksList: List<BookResult>) :
                 //          private val listener: ListItemListener) :
        RecyclerView.Adapter<BookListAdapter.ViewHolder>() {

      //  val selectedNotes = arrayListOf<NoteEntity>()

        inner class ViewHolder(itemView: View) :
            RecyclerView.ViewHolder(itemView) {
            val binding = BookGridItemBinding.bind(itemView)
        }

        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
            val inflater = LayoutInflater.from(parent.context)
            val view = inflater.inflate(R.layout.book_grid_item, parent, false)
            return ViewHolder(view)
        }

        override fun getItemCount() = booksList.size

        override fun onBindViewHolder(holder: ViewHolder, position: Int) {
            val book = booksList[position]
            with(holder.binding) {
                nameText.setText(book.volumeInfo.title)
                ratingBar?.rating = book.volumeInfo.averageRating
                Glide.with(context)
                    .load(book.volumeInfo.imageLinks.smallImageUrl)
                    .into(bookImage)

//                root.setOnClickListener{
//                    listener.editNote(note.id)
//                }

                }

            }


//        interface ListItemListener {
//            fun editNote(noteId: Int)
//            fun onItemSelectionChanged()
//        }


}
