package com.ftrd.flashlight.FileKt

import android.os.Environment
import java.io.*

/**
 * @author: Jeff <15899859876@qq.com>
 * @date:  2018-02-02 09:35
 * @description: 文件操作工具类
 */
object FileUtils {

    /**
     * 获取根目录
     */
    fun getRootDir(): String {
        return if (Environment.getExternalStorageState() == Environment.MEDIA_MOUNTED) {
            Environment.getExternalStorageDirectory().absolutePath;
        } else {
            ""
        }

    }

    /**
     * 可创建多个文件夹
     * dirPath 文件路径
     */
    fun mkDir(dirPath: String) {

        val dirArray = dirPath.split("/".toRegex())
        var pathTemp = ""
        for (i in 1 until dirArray.size) {
            pathTemp = "$pathTemp/${dirArray[i]}"
            val newF = File("${dirArray[0]}$pathTemp")
            if (!newF.exists()) {
                val cheatDir: Boolean = newF.mkdir()
                println(cheatDir)
            }
        }

    }

    /**
     * 创建文件
     *
     * dirpath 文件目录
     * fileName 文件名称
     */
   fun creatFile(dirPath: String = getRootDir(), fileName: String) {
        val file = File("$dirPath/$fileName")
        if (!file.exists()) {
            file.createNewFile()
        }

    }

    /**
     * 创建文件
     * filePath 文件路径
     */
    fun creatFile(filePath: String) {
        val file = File(filePath)
        if (!file.exists()) {
            file.createNewFile()
        }
    }

    /**
     * 创建文件
     * filePath 文件路径
     */
    fun creatFile(filePath: File) {
        if (!filePath.exists()) {
            filePath.createNewFile()
        }
    }

    /**
     * 删除文件
     *
     * dirpath 文件目录
     * fileName 文件名称
     */
    fun delFile(dirpath: String = getRootDir(), fileName: String): Boolean {
        val file = File("$dirpath/$fileName")
        if (file.checkFile()) {
            return false
        }
        return file.delete()
    }

    /**
     *  删除文件
     *  filepath 文件路径
     */
    fun delFile(filepath: File): Boolean {
        if (filepath.checkFile()) {
            return false
        }
        return filepath.delete()
    }

    /**
     *  删除文件
     *  filepath 文件路径
     */
    fun delFile(filepath: String): Boolean {
        val file = File(filepath)
        if (file.checkFile()) {
            return false
        }
        return file.delete()
    }


    /**
     * 删除文件夹
     * dirPath 文件路径
     */
    fun delDir(dirpath: String) {
        val dir = File(dirpath)
        deleteDirWihtFile(dir)
    }

    fun deleteDirWihtFile(dir: File?) {
        if (dir!!.checkFile())
            return
        for (file in dir.listFiles()) {
            if (file.isFile)
                file.delete() // 删除所有文件
            else if (file.isDirectory)
                deleteDirWihtFile(file) // 递规的方式删除文件夹
        }
        dir.delete()// 删除目录本身
    }

    private fun File.checkFile(): Boolean {
        return this == null || !this.exists() || !this.isDirectory
    }

    /**
     * 修改SD卡上的文件或目录名
     * oldFilePath 旧文件或文件夹路径
     * newFilePath 新文件或文件夹路径
     */
    fun renameFile(oldFilePath: String, newFilePath: String): Boolean {
        val oldFile = File(oldFilePath)
        val newFile = File(newFilePath)
        return oldFile.renameTo(newFile)
    }


    /**
     * 文件读取
     * filePath 文件路径
     */
    fun readFile(filePath: File): String? {
        if (!filePath.isFile) {
            return null
        } else {
            return filePath.readText()
        }
    }

    /**
     * 文件读取
     * strPath 文件路径
     */
    fun readFile(strPath: String): String? {
        return readFile(File(strPath))
    }

    /**
     * InputStream 转字符串
     */
    fun readInp(inp: InputStream): String? {
        val bytes: ByteArray = inp.readBytes()
        return String(bytes)
    }

    /**
     * BufferedReader 转字符串
     */
    fun readBuff(buff: BufferedReader): String? {
        return buff.readText()
    }

    /**
     * 写入数据
     */
    fun writeText(filePath: File, content: String) {
        creatFile(filePath)
        filePath.writeText(content)
    }

    /**
     * 追加数据
     */
    fun appendText(filePath: File, content: String) {
        creatFile(filePath)
        filePath.appendText(content)
    }

    /**
     * 追加数据
     */
    fun appendBytes(filePath: File, array: ByteArray) {
        creatFile(filePath)
        filePath.appendBytes(array)
    }

    /**
     * 获取文件大小
     */
    fun getLeng(filePath: File): Long {
        return if (!filePath.exists()) {
            -1
        } else {
            filePath.length()
        }
    }

    /**
     * 按时间排序
     */
    fun sortByTime(filePath: File): Array<File>? {
        if (!filePath.exists()) {
            return null
        }
        val files: Array<File> = filePath.listFiles()
        if (files.isEmpty()) {
            return null
        }
        files.sortBy { it.lastModified() }
        files.reverse()
        return files

    }


}
