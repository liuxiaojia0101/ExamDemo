package com.migu.schedule;


import com.migu.schedule.constants.ReturnCodeKeys;
import com.migu.schedule.info.TaskInfo;

import java.util.*;

/*
*类名和方法不能修改
 */
public class Schedule {
    List list = new ArrayList();
    Map map = new TreeMap();

    public int init() {
        if(list.size() != 0){
            for(int i=0; i<list.size(); i++){
                int nodeId = list.get(i).hashCode();
                unregisterNode(nodeId);
            }
            list.clear();
            return ReturnCodeKeys.E001;
        }else if(map.size() !=0 ){
            map.clear();
            return ReturnCodeKeys.E001;
        }
        return ReturnCodeKeys.E001;
    }


    public int registerNode(int nodeId) {
        for (int i = 0; i<list.size(); i++){
            if(list.contains(nodeId)){
                return ReturnCodeKeys.E005;
            }
        }
        if(nodeId <= 0){
            list.add(nodeId);
            return ReturnCodeKeys.E004;
        }else {
            list.add(nodeId);
            return ReturnCodeKeys.E003;
        }
    }

    public int unregisterNode(int nodeId) {
        if(nodeId <= 0){
            return ReturnCodeKeys.E004;
        }
        if(list.size() != 0){
            for(int i=0; i<list.size(); i++){
                 if(list.contains(nodeId)){
                     list.remove(i);
                     return ReturnCodeKeys.E006;
                 }else {
                     return ReturnCodeKeys.E007;
                 }
            }

        }

        return ReturnCodeKeys.E000;
    }


    public int addTask(int taskId, int consumption) {
        if(taskId <= 0){
            return ReturnCodeKeys.E009;
        }else {
            if(map.size() != 0){
                for (int i=0; i<map.size(); i++){
                    if(map.containsKey(taskId)){
                        return ReturnCodeKeys.E010;
                    }else {
                        map.put(taskId, consumption);
                        return ReturnCodeKeys.E008;
                    }
                }
            }else {
                map.put(taskId, consumption);
                return ReturnCodeKeys.E008;
            }
        }
        return 0;
    }


    public int deleteTask(int taskId) {
        if(taskId <= 0){
            return ReturnCodeKeys.E009;
        }
        if(map.size() != 0){
            for (int i=0; i<map.size(); i++){
                if(map.containsKey(taskId)){
                    map.remove(taskId);
                    return ReturnCodeKeys.E011;
                }else {
                    return ReturnCodeKeys.E012;
                }
            }
        }else {
            return ReturnCodeKeys.E012;
        }
        return 0;
    }


    public int scheduleTask(int threshold) {
        return ReturnCodeKeys.E000;
    }


    public int queryTaskStatus(List<TaskInfo> tasks) {

        if(tasks == null){
            return ReturnCodeKeys.E016;
        }
        int[] a = new int[20];
        for (int i=0; i<tasks.size(); i++){
            a[i] = tasks.get(i).getTaskId();
        }
        select(a);
        return ReturnCodeKeys.E015;
    }

    public static void select(int[] arr){
        for (int i=0; i<arr.length-1; i++)
        {
            for (int j=0; j<arr.length-1-i;j++)
            {
                if (arr[j]>arr[j+1])
                {
                    int tmp=arr[j];
                    arr[j]=arr[j+1];
                    arr[j+1]=tmp;
                }
            }
        }
    }
}
